package dv2.portfolioservice.service;

import dv2.portfolioservice.controller.payload.PortfolioCreationRequest;
import dv2.portfolioservice.domain.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    Optional<Portfolio> findPortfolioById(String portfolioId);

    List<Portfolio> listPortfolios();

    String createPortfolio(PortfolioCreationRequest portfolioCreationRequest);
}
