package dv2.portfolioservice.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = "Portfolio")
public class Portfolio {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private List<Stock> stocks = new ArrayList<>();

    public Portfolio() {
    }

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

    public List<Stock> getStocks() {
        return stocks;
    }

    public Portfolio setStocks(List<Stock> stocks) {
        this.stocks = stocks;
        return this;
    }
}
