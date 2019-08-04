import s3 = require('@aws-cdk/aws-s3');
import {Construct} from '@aws-cdk/core';
import {Bucket} from '@aws-cdk/aws-s3';
import {BucketDeployment, Source} from '@aws-cdk/aws-s3-deployment';

export interface S3FrontendProps {

}

/**
 * Create and upload the frontend to an S3 Web bucket
 *
 */
export class S3FrontEnd extends Construct {

    readonly webBucket: s3.Bucket;

    constructor(scope: Construct, id: string, props: S3FrontendProps) {
        super(scope, id);

        //Create the web bucket and give it public read access
        this.webBucket = new Bucket(this, 'MLabWebBucket', {
            websiteIndexDocument: 'index.html',
            publicReadAccess: true
        });

        //Deploy the frontend to the to the web bucket
        new BucketDeployment(this, 'DeployFrontend', {
            source: Source.asset('../frontend/dist'),
            destinationBucket: this.webBucket
        });
    }
}