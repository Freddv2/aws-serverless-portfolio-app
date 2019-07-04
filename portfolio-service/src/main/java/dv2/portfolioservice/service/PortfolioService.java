package dv2.portfolioservice.service;

import dv2.portfolioservice.domain.Portfolio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PortfolioService {
    Optional<Portfolio> findPortfolioById(long portfolioId);

    Iterable<Portfolio> listPortfolios();

    Portfolio createPortfolio(String portfolioName);
}
