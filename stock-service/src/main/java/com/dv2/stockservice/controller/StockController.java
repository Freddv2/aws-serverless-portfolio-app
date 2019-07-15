package com.dv2.stockservice.controller;

import com.dv2.stockservice.domain.Stock;
import com.dv2.stockservice.exception.StockNotFoundException;
import com.dv2.stockservice.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class StockController {

    private final StockService stockService;
    Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(path = "/stock/{symbol}", method = RequestMethod.GET)
    public Stock findStock(@PathVariable("symbol") String symbol) {
        Stock stock = new Stock(symbol);
        try {
            yahoofinance.Stock yahooStock = stockService.findStock(symbol, false);
            stock.setName(yahooStock.getName());
            stock.setPrice(yahooStock.getQuote().getPrice());
        } catch (StockNotFoundException e) {
            LOGGER.error("Stock " + symbol + " not found");
        }
        return stock;
    }
}
