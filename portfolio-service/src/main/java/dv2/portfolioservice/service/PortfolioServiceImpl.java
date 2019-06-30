package dv2.portfolioservice.service;

import dv2.portfolioservice.domain.Portfolio;
import dv2.portfolioservice.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Iterable<Portfolio> listPortfolios() {
        return portfolioRepository.findAll();
    }
}
