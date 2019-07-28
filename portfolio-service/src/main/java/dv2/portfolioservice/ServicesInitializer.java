package dv2.portfolioservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import dv2.portfolioservice.controller.PortfolioController;
import dv2.portfolioservice.repository.PortfolioRepository;
import dv2.portfolioservice.repository.PortfolioRepositoryImpl;
import dv2.portfolioservice.service.PortfolioService;
import dv2.portfolioservice.service.PortfolioServiceImpl;

public class ServicesInitializer {
    private PortfolioController portfolioController;
    private PortfolioService portfolioService;
    private PortfolioRepository portfolioRepository;

    public ServicesInitializer() {
        initServices();
    }

    private void initServices() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        portfolioRepository = new PortfolioRepositoryImpl(amazonDynamoDB);
        portfolioService = new PortfolioServiceImpl(portfolioRepository);
        portfolioController = new PortfolioController(portfolioService);
    }

    public PortfolioController getPortfolioController() {
        return portfolioController;
    }

    public PortfolioService getPortfolioService() {
        return portfolioService;
    }

    public PortfolioRepository getPortfolioRepository() {
        return portfolioRepository;
    }
}
