package com.marko.investment.portfolio.service;


import com.marko.investment.portfolio.domain.Investment;
import com.marko.investment.portfolio.domain.Portfolio;
import com.marko.investment.portfolio.dto.InvestmentRequest;
import com.marko.investment.portfolio.dto.InvestmentResponse;
import com.marko.investment.portfolio.exception.ResourceNotFoundException;
import com.marko.investment.portfolio.mapper.InvestmentMapper;
import com.marko.investment.portfolio.repository.InvestmentRepository;
import com.marko.investment.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final PortfolioRepository portfolioRepository;
    private final InvestmentMapper investmentMapper;

    public InvestmentResponse create(InvestmentRequest request) {

        Portfolio portfolio = portfolioRepository.findById(
                request.portfolioId()
        ).orElseThrow(() -> new ResourceNotFoundException(
                "Portfolio not found with id: " + request.portfolioId()
        ));

        Investment investment = investmentMapper.toEntity(
                request,
                portfolio
        );

        Investment savedInvestment =
                investmentRepository.save(investment);

        return investmentMapper.toResponse(savedInvestment);
    }
}
