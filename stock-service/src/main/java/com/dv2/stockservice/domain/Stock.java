package com.dv2.stockservice.domain;

import java.math.BigDecimal;

public class Stock {
    private String symbol;
    private String name;
    private BigDecimal price;

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Stock setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getName() {
        return name;
    }

    public Stock setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Stock setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
