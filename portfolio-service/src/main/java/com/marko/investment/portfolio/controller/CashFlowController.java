package com.marko.investment.portfolio.controller;

import com.marko.investment.portfolio.dto.CashFlowRequest;
import com.marko.investment.portfolio.dto.CashFlowResponse;
import com.marko.investment.portfolio.service.CashFlowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cashflows")
@RequiredArgsConstructor
public class CashFlowController {

    private final CashFlowService cashFlowService;

    @PostMapping
    public CashFlowResponse create(
            @Valid @RequestBody CashFlowRequest request) {

        return cashFlowService.create(request);
    }
}