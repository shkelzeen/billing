package com.billing.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BillingPeriodModel {
    private final String periodId;
    private final LocalDate from;
    private final LocalDate to;
}
