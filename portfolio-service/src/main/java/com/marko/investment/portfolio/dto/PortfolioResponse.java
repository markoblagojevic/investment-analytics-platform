package com.marko.investment.portfolio.dto;


public record PortfolioResponse(
        Long id,
        String name,
        String ownerName
) {
}