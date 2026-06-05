package com.marko.investment.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvestmentRequest(
        @NotBlank(message = "Investment name is required")
        String name,

        @NotBlank(message = "Asset class is required")
        String assetClass,

        @NotNull(message = "Portfolio id is required")
        Long portfolioId
) {
}