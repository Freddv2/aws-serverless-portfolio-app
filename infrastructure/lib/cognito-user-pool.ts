import cdk = require('@aws-cdk/core');
import cognito = require('@aws-cdk/aws-cognito');
import {CfnUserPool, UserPool, UserPoolClient} from '@aws-cdk/aws-cognito';

export interface CognitoUserPoolProps {

}

export class CognitoUserPool extends cdk.Construct {
    readonly userPool: UserPool;
    readonly cfnUserPool: CfnUserPool;
    readonly userPoolClient: UserPoolClient;

    constructor(scope: cdk.Construct, id: string, props: CognitoUserPoolProps) {
        super(scope, id);

        //Create User Pool
        this.userPool = new cognito.UserPool(this, 'AppUserPool', {
            signInType: cognito.SignInType.EMAIL,
            autoVerifiedAttributes: [
                cognito.UserPoolAttribute.EMAIL
            ]
        });

        //Edit User pool with unsupported high level props
        this.cfnUserPool = this.userPool.node.defaultChild as cognito.CfnUserPool;
        this.cfnUserPool.policies = {
            passwordPolicy: {
                minimumLength: 8,
                requireLowercase: false,
                requireNumbers: false,
                requireUppercase: false,
                requireSymbols: false
            }
        };

        //Create App Client
        this.userPoolClient = new cognito.UserPoolClient(this, 'AppUserPoolClient', {
            generateSecret: false,
            userPool: this.userPool,
            userPoolClientName: 'AppUserPoolClient'
        });

    }
}
