package com.dv2.stockservice.controller;

import com.dv2.stockservice.exception.StockNotFoundException;
import com.dv2.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost")
public class StockServiceController {

    private final StockService stockService;

    @Autowired
    public StockServiceController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public Stock findStock(@PathVariable("symbol") final String symbol) {
        try {
            return stockService.findStock(symbol, false);
        } catch (StockNotFoundException e) {
            return new Stock(symbol);
        }
    }
}
