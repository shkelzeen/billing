package com.billing.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreateBillResponseModel {
    private final Long id;
    private final String billingPeriodId;
    private final LocalDateTime billingDate;
}
