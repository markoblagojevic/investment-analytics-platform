package com.marko.investment.portfolio.controller;

import com.marko.investment.portfolio.domain.Portfolio;
import com.marko.investment.portfolio.dto.PortfolioPerformanceResponse;
import com.marko.investment.portfolio.dto.PortfolioRequest;
import com.marko.investment.portfolio.dto.PortfolioResponse;
import com.marko.investment.portfolio.service.PortfolioPerformanceService;
import com.marko.investment.portfolio.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor

public class PortfolioController {

    private final PortfolioPerformanceService portfolioPerformanceService;

    private final PortfolioService portfolioService;

    @GetMapping
    public List<PortfolioResponse> findAll() {
        return portfolioService.findAll();
    }

    @PostMapping
    public PortfolioResponse create(@Valid @RequestBody PortfolioRequest request) {
        return portfolioService.create(request);
    }
    @GetMapping("/{id}/performance")
    public PortfolioPerformanceResponse getPerformance(
            @PathVariable("id") Long id) {

        return portfolioPerformanceService.calculate(id);
    }


}
