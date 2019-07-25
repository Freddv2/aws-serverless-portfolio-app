package com.dv2.stockservice;

import com.dv2.stockservice.controller.StockController;
import com.dv2.stockservice.service.StockService;
import com.dv2.stockservice.service.StockServiceImpl;

public class ServicesInitializer {

    private StockService stockService;
    private StockController stockController;

    public ServicesInitializer() {
        initServices();
    }

    private void initServices() {
        stockService = new StockServiceImpl();
        stockController = new StockController(stockService);
    }

    public StockService getStockService() {
        return stockService;
    }

    public StockController getStockController() {
        return stockController;
    }
}
