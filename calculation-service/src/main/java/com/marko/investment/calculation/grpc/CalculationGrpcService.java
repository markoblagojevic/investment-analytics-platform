package com.marko.investment.calculation.grpc;

import com.marko.investment.calculation.dto.PerformanceCalculationResult;
import com.marko.investment.calculation.service.CalculationService;
import com.marko.investment.proto.CalculationServiceGrpc;
import com.marko.investment.proto.PerformanceRequest;
import com.marko.investment.proto.PerformanceResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CalculationGrpcService
        extends CalculationServiceGrpc.CalculationServiceImplBase {

    private final CalculationService calculationService;

    @Override
    public void calculatePerformance(
            PerformanceRequest request,
            StreamObserver<PerformanceResponse> responseObserver) {

        PerformanceCalculationResult result =
                calculationService.calculatePerformance(
                        request.getCashFlowsList()
                );

        PerformanceResponse response = PerformanceResponse.newBuilder()
                .setPortfolioId(request.getPortfolioId())
                .setTotalContributions(result.totalContributions().toString())
                .setTotalDistributions(result.totalDistributions().toString())
                .setCurrentNav(result.currentNav().toString())
                .setProfit(result.profit().toString())
                .setSimpleReturn(result.simpleReturn().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}