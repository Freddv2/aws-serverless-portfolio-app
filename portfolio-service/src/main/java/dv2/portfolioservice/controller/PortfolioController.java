package dv2.portfolioservice.controller;

import dv2.portfolioservice.domain.Portfolio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/portfolio-service")
public class PortfolioController {

    @GetMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable long portfolioId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
