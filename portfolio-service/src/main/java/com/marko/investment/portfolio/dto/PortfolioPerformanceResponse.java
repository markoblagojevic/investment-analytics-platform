package com.marko.investment.portfolio.dto;

import java.math.BigDecimal;

public record PortfolioPerformanceResponse(
        Long portfolioId,
        BigDecimal totalContributions,
        BigDecimal totalDistributions,
        BigDecimal currentNav,
        BigDecimal profit,
        BigDecimal simpleReturn
) {
}