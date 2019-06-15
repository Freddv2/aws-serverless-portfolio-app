package com.dv2.stockservice.service;

import com.dv2.stockservice.exception.StockNotFoundException;
import yahoofinance.Stock;

public interface StockService {
    Stock findStock(String symbol, boolean includeHistorical) throws StockNotFoundException;
}
