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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioPerformanceService {

    private final StringRedisTemplate redisTemplate;



    private final CashFlowRepository cashFlowRepository;

    private final CalculationGrpcClient calculationGrpcClient;
    public PortfolioPerformanceResponse calculate(Long portfolioId) {

        String cacheKey = "portfolio:performance:" + portfolioId;

        String cachedValue = redisTemplate.opsForValue().get(cacheKey);

        if (cachedValue != null) {
            System.out.println("CACHE HIT for key: " + cacheKey);

            String[] parts = cachedValue.split(";");

            return new PortfolioPerformanceResponse(
                    portfolioId,
                    new BigDecimal(parts[0]),
                    new BigDecimal(parts[1]),
                    new BigDecimal(parts[2]),
                    new BigDecimal(parts[3]),
                    new BigDecimal(parts[4])
            );
        }

        System.out.println("CACHE MISS for key: " + cacheKey);

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

        PortfolioPerformanceResponse result = new PortfolioPerformanceResponse(
                response.getPortfolioId(),
                new BigDecimal(response.getTotalContributions()),
                new BigDecimal(response.getTotalDistributions()),
                new BigDecimal(response.getCurrentNav()),
                new BigDecimal(response.getProfit()),
                new BigDecimal(response.getSimpleReturn())
        );

        String valueToCache = result.totalContributions() + ";"
                + result.totalDistributions() + ";"
                + result.currentNav() + ";"
                + result.profit() + ";"
                + result.simpleReturn();

        redisTemplate.opsForValue().set(
                cacheKey,
                valueToCache,
                Duration.ofSeconds(20)
        );
        return result;
    }
    }
