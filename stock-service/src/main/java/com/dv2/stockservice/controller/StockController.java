package com.dv2.stockservice.controller;

import com.dv2.stockservice.JsonTransformer;
import com.dv2.stockservice.domain.Stock;
import com.dv2.stockservice.service.StockService;
import com.dv2.stockservice.service.StockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import static spark.Spark.before;
import static spark.Spark.get;

public class StockController {
    private final static Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    private final static StockService stockService = new StockServiceImpl();

    public static void defineResources() {
        before((request, response) -> {
            response.type("application/json");
            LOGGER.info(requestInfoToString(request));
        });

        get("/stock/:symbol", (req, res) -> {
            String symbol = req.params(":symbol");
            Stock stock = new Stock(symbol);
            try {
                yahoofinance.Stock yahooStock = stockService.findStock(symbol, false);
                stock.setName(yahooStock.getName());
                stock.setPrice(yahooStock.getQuote().getPrice());
            } catch (Exception e) {
                LOGGER.error("Stock " + symbol + " not found");
            }
            return stock;
        }, new JsonTransformer());
    }

    private static String requestInfoToString(Request request) {
        return request.requestMethod() +
                " " + request.url() +
                " " + request.body();
    }

}
