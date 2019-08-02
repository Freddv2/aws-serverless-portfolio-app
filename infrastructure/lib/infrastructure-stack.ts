import cdk = require('@aws-cdk/core');
import lambda = require('@aws-cdk/aws-lambda');
import apigw = require('@aws-cdk/aws-apigateway');
import dynamodb = require('@aws-cdk/aws-dynamodb');


export class InfrastructureStack extends cdk.Stack {
    constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
        super(scope, id, props);

        //Stock Service
        const stockService = new lambda.Function(this, 'StockService', {
            runtime: lambda.Runtime.JAVA_8,
            code: lambda.Code.asset('../stock-service/target/stock-service-0.0.1-SNAPSHOT-lambda-package.zip'),
            handler: 'com.dv2.stockservice.StreamLambdaHandler::handleRequest',
            memorySize: 512,
            timeout: cdk.Duration.seconds(5)
        });
        let stockAPI = new apigw.LambdaRestApi(this, 'StockAPI', {
            handler: stockService,
            proxy: false
        });

        stockAPI.root.addResource('stock')
            .addResource('{symbol}')
            .addMethod('ANY');

        //Portfolio Service
        const portfolioService = new lambda.Function(this, 'portfolioService', {
            runtime: lambda.Runtime.JAVA_8,
            code: lambda.Code.asset('../portfolio-service/target/portfolio-service-0.0.1-SNAPSHOT-lambda-package.zip'),
            handler: 'dv2.portfolioservice.StreamLambdaHandler::handleRequest',
            memorySize: 512,
            timeout: cdk.Duration.seconds(5)
        });

        const portfolioTable = new dynamodb.Table(this, 'PortfolioTable', {
            tableName: 'Portfolio',
            partitionKey: {name: 'id', type: dynamodb.AttributeType.STRING},
            readCapacity: 1,
            writeCapacity: 1
        });

        portfolioTable.grantReadWriteData(portfolioService);

        portfolioService.addEnvironment('DYNAMODB', portfolioTable.tableArn);

        let portfolioAPI = new apigw.LambdaRestApi(this, 'PortfolioAPI', {
            handler: portfolioService,
            proxy: false
        });

        portfolioAPI.root.addResource('portfolios')
            .addMethod('ANY')
    }
}
