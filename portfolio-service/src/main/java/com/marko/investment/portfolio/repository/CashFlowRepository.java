package com.marko.investment.portfolio.repository;

import com.marko.investment.portfolio.domain.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

    List<CashFlow> findByInvestmentPortfolioId(Long portfolioId);
}