package dv2.portfolioservice.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "Portfolio")
public class Portfolio {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;

    @DynamoDBAttribute
    private String name;

    //private List<Stock> stocks = new ArrayList<>();

    public Portfolio(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Portfolio setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Portfolio setName(String name) {
        this.name = name;
        return this;
    }
}
