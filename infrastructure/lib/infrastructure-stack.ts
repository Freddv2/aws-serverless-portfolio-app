import cdk = require('@aws-cdk/core');
import {CognitoUserPool} from './cognito-user-pool';
import {CognitoIdentityPool} from './cognito-identity-pool';
import {S3FrontEnd} from './s3-front-end';
import {Cloudfront} from './cloudfront';
import {StockService} from './stock-service';
import {PortfolioService} from './portfolio-service';

export class InfrastructureStack extends cdk.Stack {
    constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
        super(scope, id, props);

        //Auth
        const cognitoUserPool = new CognitoUserPool(this, 'AppCognitoUserPool', {});
        const cognitoIdentityPool = new CognitoIdentityPool(this, 'AppCognitoIdentityPool', {
            userPool: cognitoUserPool.userPool,
            userPoolClient: cognitoUserPool.userPoolClient
        });

        //Frontend
        const s3Web = new S3FrontEnd(this, 'AppFrontend', {});
        const cloudFront = new Cloudfront(this, 'AppCloudFront', {
            s3Bucket: s3Web.webBucket
        });

        //Services
        const stockService = new StockService(this, 'StockService', {});
        const portfolioService = new PortfolioService(this, 'PortfolioService', {})
    }
}
