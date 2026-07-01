package com.marko.investment.calculation.service;

import com.marko.investment.calculation.dto.PerformanceCalculationResult;
import com.marko.investment.proto.CashFlowMessage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationServiceTest {

    private final CalculationService calculationService =
            new CalculationService();

    @Test
    void shouldCalculatePortfolioPerformance() {
        List<CashFlowMessage> cashFlows = List.of(
                CashFlowMessage.newBuilder()
                        .setAmount("10000")
                        .setType("CONTRIBUTION")
                        .build(),

                CashFlowMessage.newBuilder()
                        .setAmount("1500")
                        .setType("DISTRIBUTION")
                        .build(),

                CashFlowMessage.newBuilder()
                        .setAmount("12000")
                        .setType("NAV")
                        .build()
        );

        PerformanceCalculationResult result =
                calculationService.calculatePerformance(cashFlows);

        assertEquals(new BigDecimal("10000"), result.totalContributions());
        assertEquals(new BigDecimal("1500"), result.totalDistributions());
        assertEquals(new BigDecimal("12000"), result.currentNav());
        assertEquals(new BigDecimal("3500"), result.profit());
        assertEquals(new BigDecimal("35.0000"), result.simpleReturn());
    }
}