import {Construct, Duration} from '@aws-cdk/core';
import {LambdaRestApi} from '@aws-cdk/aws-apigateway';
import lambda = require('@aws-cdk/aws-lambda');

export interface StockServiceProps {

}

export class StockService extends Construct {
    constructor(scope: Construct, name: string, props: StockServiceProps) {
        super(scope, name);

        const stockService = new lambda.Function(this, 'StockService', {
            runtime: lambda.Runtime.JAVA_8,
            code: lambda.Code.asset('../stock-service/target/stock-service-0.0.1-SNAPSHOT-lambda-package.zip'),
            handler: 'com.dv2.stockservice.StreamLambdaHandler::handleRequest',
            memorySize: 512,
            timeout: Duration.seconds(5)
        });

        let stockAPI = new LambdaRestApi(this, 'StockAPI', {
            handler: stockService,
            proxy: false
        });

        stockAPI.root.addResource('stock')
            .addResource('{symbol}')
            .addMethod('ANY');
    }
}