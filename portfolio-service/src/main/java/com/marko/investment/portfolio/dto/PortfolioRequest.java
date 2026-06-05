package com.marko.investment.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

public record PortfolioRequest(
        @NotBlank(message = "Portfolio name is required")
        String name,

        @NotBlank(message = "Owner name is required")
        String ownerName
) {
}
