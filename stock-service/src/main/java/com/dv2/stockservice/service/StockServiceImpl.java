package com.dv2.stockservice.service;

import com.dv2.stockservice.exception.StockNotFoundException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

public class StockServiceImpl implements StockService {

    @Override
    public Stock findStock(String symbol, boolean includeHistorical) throws StockNotFoundException {
        try {
            return YahooFinance.get(symbol, includeHistorical);
        } catch (IOException e) {
            throw new StockNotFoundException(e);
        }
    }
}
