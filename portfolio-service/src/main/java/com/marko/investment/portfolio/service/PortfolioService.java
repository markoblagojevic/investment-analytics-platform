package com.marko.investment.portfolio.service;

import com.marko.investment.portfolio.domain.Portfolio;
import com.marko.investment.portfolio.dto.PortfolioRequest;
import com.marko.investment.portfolio.dto.PortfolioResponse;
import com.marko.investment.portfolio.mapper.PortfolioMapper;
import com.marko.investment.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    public List<PortfolioResponse> findAll() {
        return portfolioRepository.findAll()
                .stream()
                .map(portfolioMapper::toResponse)
                .toList();
    }

    public PortfolioResponse create(PortfolioRequest request) {
        Portfolio portfolio = portfolioMapper.toEntity(request);
        Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        return portfolioMapper.toResponse(savedPortfolio);
    }
}
