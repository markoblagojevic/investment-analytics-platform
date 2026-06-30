package com.marko.investment.portfolio.grpc;

import com.marko.investment.proto.CalculationServiceGrpc;
import com.marko.investment.proto.PerformanceRequest;
import com.marko.investment.proto.PerformanceResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class CalculationGrpcClient {

    @GrpcClient("calculation-service")
    private CalculationServiceGrpc.CalculationServiceBlockingStub calculationServiceBlockingStub;

    public PerformanceResponse calculatePerformance(PerformanceRequest request) {
        return calculationServiceBlockingStub.calculatePerformance(request);
    }
}