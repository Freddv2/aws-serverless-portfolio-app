package dv2.portfolioservice.service;

import dv2.portfolioservice.domain.Portfolio;
import org.springframework.stereotype.Service;

@Service
public interface PortfolioService {
    Iterable<Portfolio> listPortfolios();
}
