package com.marko.investment.portfolio.service;

import com.marko.investment.portfolio.domain.CashFlow;
import com.marko.investment.portfolio.domain.Investment;
import com.marko.investment.portfolio.dto.CashFlowRequest;
import com.marko.investment.portfolio.dto.CashFlowResponse;
import com.marko.investment.portfolio.exception.ResourceNotFoundException;
import com.marko.investment.portfolio.mapper.CashFlowMapper;
import com.marko.investment.portfolio.repository.CashFlowRepository;
import com.marko.investment.portfolio.repository.InvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashFlowService {

    private final CashFlowRepository cashFlowRepository;
    private final InvestmentRepository investmentRepository;
    private final CashFlowMapper cashFlowMapper;

    public CashFlowResponse create(CashFlowRequest request) {

        Investment investment = investmentRepository.findById(request.investmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Investment not found with id: " + request.investmentId()
                ));

        CashFlow cashFlow = cashFlowMapper.toEntity(request, investment);

        CashFlow savedCashFlow = cashFlowRepository.save(cashFlow);

        return cashFlowMapper.toResponse(savedCashFlow);
    }
}