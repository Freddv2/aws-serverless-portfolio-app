package dv2.portfolioservice.controller.payload;

import java.util.HashSet;
import java.util.Set;

public class PortfolioCreationRequest {
    private String name;
    private String description = "";
    private Set<String> symbols = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(Set<String> symbols) {
        this.symbols = symbols;
    }
}
