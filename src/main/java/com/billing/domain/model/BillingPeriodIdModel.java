package com.billing.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BillingPeriodIdModel {
    private final String periodId;
}
