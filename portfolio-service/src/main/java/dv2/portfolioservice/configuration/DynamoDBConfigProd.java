package dv2.portfolioservice.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@EnableDynamoDBRepositories(basePackages = "dv2.portfolioservice.repository")
public class DynamoDBConfigProd {

    @Value("${amazon.dynamodb.accesskey}")
    private String amazonDynamoDBAccessKey;

    @Value("${amazon.dynamodb.secretkey}")
    private String amazonDynamoDBSecretKey;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey);
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(awsCredentials());
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
