package com.billing.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
public class TransactionsModel {
    private final String accountId;
    private final String billingPeriodId;
    private final double totalValue;
    private final long count;
}
