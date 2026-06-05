package com.marko.investment.portfolio.mapper;

import com.marko.investment.portfolio.domain.Investment;
import com.marko.investment.portfolio.domain.Portfolio;
import com.marko.investment.portfolio.dto.InvestmentRequest;
import com.marko.investment.portfolio.dto.InvestmentResponse;
import org.springframework.stereotype.Component;

@Component
public class InvestmentMapper {

    public Investment toEntity(InvestmentRequest request, Portfolio portfolio) {
        Investment investment = new Investment();
        investment.setName(request.name());
        investment.setAssetClass(request.assetClass());
        investment.setPortfolio(portfolio);
        return investment;
    }

    public InvestmentResponse toResponse(Investment investment) {
        return new InvestmentResponse(
                investment.getId(),
                investment.getName(),
                investment.getAssetClass(),
                investment.getPortfolio().getId(),
                investment.getPortfolio().getName()
        );
    }
}
