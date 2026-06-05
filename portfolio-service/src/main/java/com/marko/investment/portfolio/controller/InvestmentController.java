package com.marko.investment.portfolio.controller;

import com.marko.investment.portfolio.dto.InvestmentRequest;
import com.marko.investment.portfolio.dto.InvestmentResponse;
import com.marko.investment.portfolio.service.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping
    public InvestmentResponse create(
            @Valid @RequestBody InvestmentRequest request) {

        return investmentService.create(request);
    }
}