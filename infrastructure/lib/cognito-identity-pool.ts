import {Effect, FederatedPrincipal, PolicyStatement, Role} from '@aws-cdk/aws-iam';
import {CfnIdentityPool, CfnIdentityPoolRoleAttachment, UserPool, UserPoolClient} from '@aws-cdk/aws-cognito';
import {Construct} from '@aws-cdk/core';

export interface CognitoIdentityPoolProps {
    readonly userPool: UserPool;
    readonly userPoolClient: UserPoolClient;
}

/**
 * Identity pool definition. An identity pool contains
 * the authorization of our users. Users are provided
 * to the identity pool. The identity pool assign an
 * identity to the user which allow the user to be
 * authorized in our application
 *
 * https://serverless-stack.com/chapters/cognito-user-pool-vs-identity-pool.html
 *
 */
export class CognitoIdentityPool extends Construct {

    readonly identityPool: CfnIdentityPool;
    readonly authenticatedRole: Role;
    readonly unauthenticatedRole: Role;
    readonly defaultPolicy: CfnIdentityPoolRoleAttachment;

    constructor(scope: Construct, id: string, props: CognitoIdentityPoolProps) {
        super(scope, id);

        //Create identity pool
        this.identityPool = new CfnIdentityPool(this, 'CognitoIdentityPool', {
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
            effect: Effect.ALLOW,
            actions: [
                'mobileanalytics:PutEvents',
                'cognito-sync:*',
                'cognito-identity:*'
            ],
            resources: ['*'],
        }));

        this.defaultPolicy = new CfnIdentityPoolRoleAttachment(this, 'DefaultValid', {
            identityPoolId: this.identityPool.ref,
            roles: {
                'unauthenticated': this.unauthenticatedRole.roleArn,
                'authenticated': this.authenticatedRole.roleArn
            }
        });
    }
}
