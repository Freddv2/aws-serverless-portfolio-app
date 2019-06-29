package dv2.portfolioservice.domain;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private String name;
    private List<Stock> stocks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Portfolio setName(String name) {
        this.name = name;
        return this;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public Portfolio setStocks(List<Stock> stocks) {
        this.stocks = stocks;
        return this;
    }
}
