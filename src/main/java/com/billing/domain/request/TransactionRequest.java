package com.billing.domain.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class TransactionRequest {
    private final String accountId;
    private final double value;
    private final LocalDate date;
}
