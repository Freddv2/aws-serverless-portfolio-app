package dv2.portfolioservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "symbol")
    private String symbol;

    public long getId() {
        return id;
    }

    public Stock setId(long id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Stock setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
}
