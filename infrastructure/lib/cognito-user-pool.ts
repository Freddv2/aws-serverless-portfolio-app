import cdk = require('@aws-cdk/core');
import {CfnUserPool, SignInType, UserPool, UserPoolAttribute, UserPoolClient} from '@aws-cdk/aws-cognito';
import {Construct} from '@aws-cdk/core';

export interface CognitoUserPoolProps {

}

/**
 * User pool definition. A user pool contains the list
 * of users the application
 *
 */
export class CognitoUserPool extends Construct {
    readonly userPool: UserPool;
    readonly cfnUserPool: CfnUserPool;
    readonly userPoolClient: UserPoolClient;

    constructor(scope: cdk.Construct, id: string, props: CognitoUserPoolProps) {
        super(scope, id);

        //Create User Pool
        this.userPool = new UserPool(this, 'CognitoUserPool', {
            signInType: SignInType.EMAIL,
            autoVerifiedAttributes: [
                UserPoolAttribute.EMAIL
            ]
        });

        //Edit User pool with level 1 construct because those props are currently
        //not supported in the level 2.
        this.cfnUserPool = this.userPool.node.defaultChild as CfnUserPool;
        this.cfnUserPool.policies = {
            passwordPolicy: {
                minimumLength: 8,
                requireLowercase: false,
                requireNumbers: false,
                requireUppercase: false,
                requireSymbols: false
            }
        };

        //Create App Client. An app client is a client that a allow unauthenticated user to login & register 
        this.userPoolClient = new UserPoolClient(this, 'CognitoUserPoolClient', {
            generateSecret: false,
            userPool: this.userPool
        });

    }
}
