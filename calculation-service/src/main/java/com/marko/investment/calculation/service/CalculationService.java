package com.marko.investment.calculation.service;

import com.marko.investment.calculation.dto.PerformanceCalculationResult;
import com.marko.investment.proto.CashFlowMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculationService {

    public PerformanceCalculationResult calculatePerformance(
            List<CashFlowMessage> cashFlows) {

        BigDecimal totalContributions = BigDecimal.ZERO;
        BigDecimal totalDistributions = BigDecimal.ZERO;
        BigDecimal currentNav = BigDecimal.ZERO;

        for (CashFlowMessage cashFlow : cashFlows) {

            BigDecimal amount = new BigDecimal(cashFlow.getAmount());

            switch (cashFlow.getType()) {

                case "CONTRIBUTION":
                    totalContributions = totalContributions.add(amount);
                    break;

                case "DISTRIBUTION":
                    totalDistributions = totalDistributions.add(amount);
                    break;

                case "NAV":
                    currentNav = amount;
                    break;

                default:
                    break;
            }
        }

        BigDecimal profit = currentNav
                .add(totalDistributions)
                .subtract(totalContributions);

        BigDecimal simpleReturn = BigDecimal.ZERO;

        if (totalContributions.compareTo(BigDecimal.ZERO) > 0) {
            simpleReturn = profit
                    .divide(totalContributions, 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
        }

        return new PerformanceCalculationResult(
                totalContributions,
                totalDistributions,
                currentNav,
                profit,
                simpleReturn
        );
    }
}