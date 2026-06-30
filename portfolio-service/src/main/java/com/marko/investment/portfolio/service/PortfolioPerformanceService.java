package com.marko.investment.portfolio.service;

import com.marko.investment.portfolio.domain.CashFlow;
import com.marko.investment.portfolio.domain.CashFlowType;
import com.marko.investment.portfolio.dto.PortfolioPerformanceResponse;
import com.marko.investment.portfolio.grpc.CalculationGrpcClient;
import com.marko.investment.portfolio.repository.CashFlowRepository;
import com.marko.investment.proto.CashFlowMessage;
import com.marko.investment.proto.PerformanceRequest;
import com.marko.investment.proto.PerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioPerformanceService {

    private final CashFlowRepository cashFlowRepository;

    private final CalculationGrpcClient calculationGrpcClient;
    public PortfolioPerformanceResponse calculate(Long portfolioId) {

        List<CashFlow> cashFlows =
                cashFlowRepository.findByInvestmentPortfolioId(portfolioId);

        PerformanceRequest.Builder requestBuilder =
                PerformanceRequest.newBuilder()
                        .setPortfolioId(portfolioId);

        cashFlows.forEach(cashFlow ->
                requestBuilder.addCashFlows(
                        CashFlowMessage.newBuilder()
                                .setAmount(cashFlow.getAmount().toString())
                                .setType(cashFlow.getType().name())
                                .build()
                )
        );

        PerformanceResponse response =
                calculationGrpcClient.calculatePerformance(requestBuilder.build());

        return new PortfolioPerformanceResponse(
                response.getPortfolioId(),
                new BigDecimal(response.getTotalContributions()),
                new BigDecimal(response.getTotalDistributions()),
                new BigDecimal(response.getCurrentNav()),
                new BigDecimal(response.getProfit()),
                new BigDecimal(response.getSimpleReturn())
        );
    }
    }
