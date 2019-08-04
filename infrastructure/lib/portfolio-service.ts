import {Construct, Duration} from '@aws-cdk/core';
import {AttributeType, Table} from '@aws-cdk/aws-dynamodb';
import {LambdaRestApi} from '@aws-cdk/aws-apigateway';
import {Code, Runtime} from '@aws-cdk/aws-lambda';
import lambda = require('@aws-cdk/aws-lambda');


export interface PortfolioServiceProps {

}

export class PortfolioService extends Construct {
    constructor(scope: Construct, name: string, props: PortfolioServiceProps) {
        super(scope, name);

        const portfolioService = new lambda.Function(this, 'portfolioService', {
            runtime: Runtime.JAVA_8,
            code: Code.asset('../portfolio-service/target/portfolio-service-0.0.1-SNAPSHOT-lambda-package.zip'),
            handler: 'dv2.portfolioservice.StreamLambdaHandler::handleRequest',
            memorySize: 512,
            timeout: Duration.seconds(5)
        });

        const portfolioTable = new Table(this, 'PortfolioTable', {
            tableName: 'Portfolio',
            partitionKey: {name: 'id', type: AttributeType.STRING},
            readCapacity: 1,
            writeCapacity: 1
        });

        portfolioTable.grantReadWriteData(portfolioService);

        portfolioService.addEnvironment('DYNAMODB', portfolioTable.tableArn);

        let portfolioAPI = new LambdaRestApi(this, 'PortfolioAPI', {
            handler: portfolioService,
            proxy: false
        });

        portfolioAPI.root.addResource('portfolios')
            .addMethod('ANY')
    }
}