package com.dv2.stockservice.service;

import com.dv2.stockservice.exception.StockNotFoundException;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@Service
public class StockServiceImpl implements StockService {

    @Override
    public Stock findStock(String symbol, boolean includeHistorical) throws StockNotFoundException {
        Stock stock;
        try {
            stock = YahooFinance.get(symbol, includeHistorical);
        } catch (IOException e) {
            throw new StockNotFoundException(e);
        }
        return stock;
    }
}
