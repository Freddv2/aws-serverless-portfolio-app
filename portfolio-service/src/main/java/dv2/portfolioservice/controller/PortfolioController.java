package dv2.portfolioservice.controller;

import dv2.portfolioservice.domain.Portfolio;
import dv2.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio-service")
@CrossOrigin(origins = "http://localhost")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/portfolios")
    public ResponseEntity<Iterable<Portfolio>> listPortfolios() {
        Iterable<Portfolio> portfolios = portfolioService.listPortfolios();
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    @GetMapping("/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable long portfolioId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
