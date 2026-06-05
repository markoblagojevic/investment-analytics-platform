package com.marko.investment.portfolio.mapper;

import com.marko.investment.portfolio.domain.Portfolio;
import com.marko.investment.portfolio.dto.PortfolioRequest;
import com.marko.investment.portfolio.dto.PortfolioResponse;
import org.springframework.stereotype.Component;

@Component


public class PortfolioMapper {

    public Portfolio toEntity(PortfolioRequest request) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(request.name());
        portfolio.setOwnerName(request.ownerName());
        return portfolio;
    }

    public PortfolioResponse toResponse(Portfolio portfolio) {
        return new PortfolioResponse(
                portfolio.getId(),
                portfolio.getName(),
                portfolio.getOwnerName()
        );
    }
}
