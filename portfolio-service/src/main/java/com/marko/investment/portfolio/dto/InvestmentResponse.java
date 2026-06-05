package com.marko.investment.portfolio.dto;

public record InvestmentResponse(
        Long id,
        String name,
        String assetClass,
        Long portfolioId,
        String portfolioName
) {
}