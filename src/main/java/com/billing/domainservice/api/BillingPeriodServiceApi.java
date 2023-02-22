package com.billing.domainservice.api;

import com.billing.BillingPeriod;
import com.billing.BillingPeriodServiceGrpc.BillingPeriodServiceImplBase;
import com.billing.domain.model.BillingPeriodIdModel;
import com.billing.domainservice.BillingPeriodService;
import com.billing.exception.BillingPeriodNotFoundException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.Instant;
import java.time.ZoneId;


///In a real case this would be on a separate module since that model
// is meant to be used for microservice communication
@GrpcService
@RequiredArgsConstructor
public class BillingPeriodServiceApi extends BillingPeriodServiceImplBase {

    private BillingPeriodService billingPeriodService;

    @Override
    public void getBillingPeriod(BillingPeriod.BillingPeriodRequestDto request,
                                 StreamObserver<BillingPeriod.BillingPeriodResponseDto> responseObserver) {
        try {
            BillingPeriodIdModel billingPeriodForDate = billingPeriodService
                    .getBillingPeriodForDate(Instant.ofEpochMilli(request.getDate())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate());
            BillingPeriod.BillingPeriodResponseDto.Builder builder = BillingPeriod.BillingPeriodResponseDto.newBuilder();
            builder.setPeriodId(billingPeriodForDate.getPeriodId());
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(new Throwable(String.format("Error while parsing input %s please make sure to have a valid date",
                    request.getDate())));
            responseObserver.onCompleted();
        } catch (BillingPeriodNotFoundException e) {
            responseObserver.onError(new Throwable(e.getMessage()));
            responseObserver.onCompleted();
        }

    }
}
