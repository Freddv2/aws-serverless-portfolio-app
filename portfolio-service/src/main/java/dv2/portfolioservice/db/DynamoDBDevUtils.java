package dv2.portfolioservice.db;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

public class DynamoDBDevUtils {

    public static void createTable(Class entity, AmazonDynamoDB amazonDynamoDB) {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(entity)
                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }
}
