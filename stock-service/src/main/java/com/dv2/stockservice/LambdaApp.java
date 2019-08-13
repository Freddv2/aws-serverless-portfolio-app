package com.dv2.stockservice;

import spark.Spark;

public class LambdaApp {
    public static void main(String[] args) {
        ServicesInitializer servicesInitializer = new ServicesInitializer();
        Spark.port(8080);
        servicesInitializer.getStockController().defineResources();
        Spark.awaitInitialization();
    }
}
