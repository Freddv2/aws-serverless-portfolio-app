package dv2.portfolioservice.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "portfolio")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();

    public Portfolio() {
    }

    public Portfolio(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Portfolio setId(long id) {
        this.id = id;
        return this;
    }

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
