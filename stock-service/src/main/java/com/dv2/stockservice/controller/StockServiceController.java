package com.dv2.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@RestController
@RequestMapping("/stock")
public class StockServiceController {

    @GetMapping("/{symbol}")
    public Stock getStock(@PathVariable("symbol") final String symbol) {
        Stock stock;
        try {
            stock = YahooFinance.get("symbol", false);
        } catch (IOException e) {
            stock = new Stock(symbol);
        }
        return stock;
    }
}
