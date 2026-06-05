package com.marko.investment.portfolio.service;

import com.marko.investment.portfolio.domain.CashFlow;
import com.marko.investment.portfolio.domain.CashFlowType;
import com.marko.investment.portfolio.dto.PortfolioPerformanceResponse;
import com.marko.investment.portfolio.repository.CashFlowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioPerformanceService {

    private final CashFlowRepository cashFlowRepository;

    public PortfolioPerformanceResponse calculate(Long portfolioId) {

        List<CashFlow> cashFlows =
                cashFlowRepository.findByInvestmentPortfolioId(portfolioId);

        BigDecimal totalContributions = sumByType(
                cashFlows,
                CashFlowType.CONTRIBUTION
        );

        BigDecimal totalDistributions = sumByType(
                cashFlows,
                CashFlowType.DISTRIBUTION
        );

        BigDecimal currentNav = sumByType(
                cashFlows,
                CashFlowType.NAV
        );

        BigDecimal profit = totalDistributions
                .add(currentNav)
                .subtract(totalContributions);

        BigDecimal simpleReturn = BigDecimal.ZERO;

        if (totalContributions.compareTo(BigDecimal.ZERO) > 0) {
            simpleReturn = profit.divide(
                    totalContributions,
                    4,
                    RoundingMode.HALF_UP
            );
        }

        return new PortfolioPerformanceResponse(
                portfolioId,
                totalContributions,
                totalDistributions,
                currentNav,
                profit,
                simpleReturn
        );
    }

    private BigDecimal sumByType(
            List<CashFlow> cashFlows,
            CashFlowType type) {

        return cashFlows.stream()
                .filter(cashFlow -> cashFlow.getType() == type)
                .map(CashFlow::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}