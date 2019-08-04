import {Construct} from '@aws-cdk/core';
import {Bucket} from '@aws-cdk/aws-s3';
import {CloudFrontWebDistribution} from '@aws-cdk/aws-cloudfront';


export interface CloudfrontProps {
    readonly s3Bucket: Bucket;
}

/**
 * Create and upload the frontend to an S3 Web bucket
 *
 */
export class Cloudfront extends Construct {
    readonly cloudfront: CloudFrontWebDistribution;

    constructor(scope: Construct, id: string, props: CloudfrontProps) {
        super(scope, id);

        this.cloudfront = new CloudFrontWebDistribution(this, 'CloudFrontFrontendDistribution', {
            originConfigs: [
                {
                    s3OriginSource: {
                        s3BucketSource: props.s3Bucket
                    },
                    behaviors: [{isDefaultBehavior: true}]
                }
            ]
        })
    }
}