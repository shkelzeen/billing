package com.billing.domain.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder(toBuilder = true)
public class CreateBillRequest {
    private final String billingPeriodId;
    private final LocalDateTime billingDate;
}
