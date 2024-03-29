package dv2.portfolioservice.repository;

import dv2.portfolioservice.domain.Portfolio;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
}
