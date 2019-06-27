package com.dv2.stockservice.controller;

import com.dv2.stockservice.domain.Stock;
import com.dv2.stockservice.exception.StockNotFoundException;
import com.dv2.stockservice.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost")
public class StockServiceController {

    Logger LOGGER = LoggerFactory.getLogger(StockServiceController.class);

    private final StockService stockService;

    @Autowired
    public StockServiceController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<Stock> findStock(@PathVariable("symbol") final String symbol) {
        Stock stock = new Stock(symbol);
        try {
            yahoofinance.Stock yahooStock = stockService.findStock(symbol, false);
            stock.setName(yahooStock.getName());
            stock.setPrice(yahooStock.getQuote().getPrice());
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (StockNotFoundException e) {
            LOGGER.error("Stock " + symbol + " not found");
            return new ResponseEntity<>(stock, HttpStatus.NOT_FOUND);
        }
    }
}
