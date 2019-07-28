package dv2.portfolioservice.repository;

import dv2.portfolioservice.domain.Portfolio;

import java.util.List;

public interface PortfolioRepository {
    String save(Portfolio portfolio);

    Portfolio findById(String id);

    List<Portfolio> findAll();
}
  