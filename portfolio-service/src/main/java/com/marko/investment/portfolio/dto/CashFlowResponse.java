package com.marko.investment.portfolio.dto;

import com.marko.investment.portfolio.domain.CashFlowType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CashFlowResponse(
        Long id,
        BigDecimal amount,
        LocalDate date,
        CashFlowType type,
        Long investmentId,
        String investmentName
) {
}