import cdk = require('@aws-cdk/core');
import cognito = require('@aws-cdk/aws-cognito');
import iam = require('@aws-cdk/aws-iam');
import {Effect, FederatedPrincipal, PolicyStatement, Role} from '@aws-cdk/aws-iam';
import {UserPool, UserPoolClient} from '@aws-cdk/aws-cognito';

export interface CognitoIdentityPoolProps {
    readonly userPool: UserPool;
    readonly userPoolClient: UserPoolClient;
}

export class CognitoIdentityPool extends cdk.Construct {

    readonly identityPool: cognito.CfnIdentityPool;
    readonly authenticatedRole: Role;
    readonly unauthenticatedRole: Role;
    readonly defaultPolicy: cognito.CfnIdentityPoolRoleAttachment;

    constructor(scope: cdk.Construct, id: string, props: CognitoIdentityPoolProps) {
        super(scope, id);

        //Create identity pool. An identity pool is required to authorize users from the user pool
        this.identityPool = new cognito.CfnIdentityPool(this, 'AppIdentityPool', {
            allowUnauthenticatedIdentities: false,
            cognitoIdentityProviders: [{
                providerName: props.userPool.userPoolProviderName,
                clientId: props.userPoolClient.userPoolClientId
            }]
        });

        //Define an unauthenticated role in our identity pools. This will be the role assigned to
        //an unauthenticated user.
        this.unauthenticatedRole = new Role(this, 'CognitoDefaultUnauthenticatedRole', {
            assumedBy: new FederatedPrincipal('cognito-identity.amazonaws.com', {
                'StringEquals': {'cognito-identity.amazonaws.com:aud': this.identityPool.ref},
                'ForAnyValue:StringLike': {'cognito-identity.amazonaws.com:amr': 'unauthenticated'},
            }, 'sts:AssumeRoleWithWebIdentity'),
        });
        this.unauthenticatedRole.addToPolicy(new PolicyStatement({
            effect: Effect.ALLOW,
            actions: [
                'mobileanalytics:PutEvents',
                'cognito-sync:*'
            ],
            resources: ['*'],
        }));

        //Define an authenticated role in our identity pools. This will be the role assigned to
        //an authenticated user.
        this.authenticatedRole = new Role(this, 'CognitoDefaultAuthenticatedRole', {
            assumedBy: new FederatedPrincipal('cognito-identity.amazonaws.com', {
                'StringEquals': {'cognito-identity.amazonaws.com:aud': this.identityPool.ref},
                'ForAnyValue:StringLike': {'cognito-identity.amazonaws.com:amr': 'authenticated'},
            }, 'sts:AssumeRoleWithWebIdentity'),
        });
        this.authenticatedRole.addToPolicy(new PolicyStatement({
            effect: iam.Effect.ALLOW,
            actions: [
                'mobileanalytics:PutEvents',
                'cognito-sync:*',
                'cognito-identity:*'
            ],
            resources: ['*'],
        }));

        this.defaultPolicy = new cognito.CfnIdentityPoolRoleAttachment(this, 'DefaultValid', {
            identityPoolId: this.identityPool.ref,
            roles: {
                'unauthenticated': this.unauthenticatedRole.roleArn,
                'authenticated': this.authenticatedRole.roleArn
            }
        });
    }
}
