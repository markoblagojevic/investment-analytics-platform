package com.marko.investment.portfolio.mapper;

import com.marko.investment.portfolio.domain.CashFlow;
import org.springframework.stereotype.Component;


import com.marko.investment.portfolio.domain.CashFlow;
import com.marko.investment.portfolio.domain.Investment;
import com.marko.investment.portfolio.dto.CashFlowRequest;
import com.marko.investment.portfolio.dto.CashFlowResponse;
import org.springframework.stereotype.Component;
@Component
public class CashFlowMapper {

    public CashFlow toEntity(
            CashFlowRequest request,
            Investment investment) {

        CashFlow cashFlow = new CashFlow();

        cashFlow.setAmount(request.amount());
        cashFlow.setDate(request.date());
        cashFlow.setType(request.type());
        cashFlow.setInvestment(investment);

        return cashFlow;
    }

    public CashFlowResponse toResponse(
            CashFlow cashFlow) {

        return new CashFlowResponse(
                cashFlow.getId(),
                cashFlow.getAmount(),
                cashFlow.getDate(),
                cashFlow.getType(),
                cashFlow.getInvestment().getId(),
                cashFlow.getInvestment().getName()
        );
    }
}