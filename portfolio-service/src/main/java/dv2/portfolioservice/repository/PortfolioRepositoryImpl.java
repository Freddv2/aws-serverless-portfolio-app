package dv2.portfolioservice.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import dv2.portfolioservice.domain.Portfolio;

import java.util.List;

public class PortfolioRepositoryImpl implements PortfolioRepository {

    DynamoDBMapper dynamoDBMapper;

    public PortfolioRepositoryImpl(AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @Override
    public String save(Portfolio portfolio) {
        dynamoDBMapper.save(portfolio);
        return portfolio.getId();
    }

    @Override
    public Portfolio findById(String id) {
        return dynamoDBMapper.load(Portfolio.class, id);
    }

    @Override
    public List<Portfolio> findAll() {
        return dynamoDBMapper.scan(Portfolio.class, new DynamoDBScanExpression());
    }
}
