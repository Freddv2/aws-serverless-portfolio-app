package dv2.portfolioservice;

import spark.Spark;

public class LambdaApp {
    public static void main(String[] args) {
        ServicesInitializer servicesInitializer = new ServicesInitializer();
        servicesInitializer.getPortfolioController().defineResources();
        Spark.awaitInitialization();
    }
}
