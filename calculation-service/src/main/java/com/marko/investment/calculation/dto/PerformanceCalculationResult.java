package com.marko.investment.calculation.dto;

import java.math.BigDecimal;

public record PerformanceCalculationResult(
        BigDecimal totalContributions,
        BigDecimal totalDistributions,
        BigDecimal currentNav,
        BigDecimal profit,
        BigDecimal simpleReturn
) {
}