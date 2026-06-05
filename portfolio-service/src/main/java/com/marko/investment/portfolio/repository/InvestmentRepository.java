package com.marko.investment.portfolio.repository;

import com.marko.investment.portfolio.domain.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
