package dv2.portfolioservice.service;

import dv2.portfolioservice.controller.payload.PortfolioCreationRequest;
import dv2.portfolioservice.domain.Portfolio;
import dv2.portfolioservice.repository.PortfolioRepository;

import java.util.List;
import java.util.Optional;

public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Optional<Portfolio> findPortfolioById(String portfolioId) {
        return Optional.of(portfolioRepository.findById(portfolioId));
    }

    @Override
    public List<Portfolio> listPortfolios() {
        return portfolioRepository.findAll();
    }

    @Override
    public String createPortfolio(PortfolioCreationRequest portfolioCreationRequest) {

        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioCreationRequest.getName());
        portfolio.setSymbols(portfolioCreationRequest.getSymbols());
        portfolioRepository.save(portfolio);

        return portfolio.getId();
    }
}
