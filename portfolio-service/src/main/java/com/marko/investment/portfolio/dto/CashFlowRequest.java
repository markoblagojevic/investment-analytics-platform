package com.marko.investment.portfolio.dto;

import com.marko.investment.portfolio.domain.CashFlowType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CashFlowRequest(
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Cash flow type is required")
        CashFlowType type,

        @NotNull(message = "Investment id is required")
        Long investmentId
) {
}