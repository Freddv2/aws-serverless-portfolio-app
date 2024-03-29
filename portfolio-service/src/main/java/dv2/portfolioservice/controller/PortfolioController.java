package dv2.portfolioservice.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import dv2.portfolioservice.domain.Portfolio;
import dv2.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/portfolio-service")
@CrossOrigin(origins = "http://localhost")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/portfolios")
    public Portfolio createPortfolio(@RequestBody ObjectNode requestBody) {
        return portfolioService.createPortfolio(requestBody.get("portfolioName").asText());
    }

    @GetMapping("/portfolios")
    public Iterable<Portfolio> listPortfolios() {
        return portfolioService.listPortfolios();
    }

    @GetMapping("/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable long portfolioId) {
        Optional<Portfolio> portfolio = portfolioService.findPortfolioById(portfolioId);
        if (portfolio.isPresent()) {
            return ResponseEntity.ok(portfolio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
