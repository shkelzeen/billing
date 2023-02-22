package com.billing.domainservice.impl;

import com.billing.domain.model.BillingPeriodIdModel;
import com.billing.domain.model.TransactionsModel;
import com.billing.domain.request.TransactionRequest;
import com.billing.domainservice.BillingPeriodService;
import com.billing.domainservice.TransactionService;
import com.billing.exception.BillingPeriodNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BillingPeriodService billingPeriodService;
    private Map<String, TransactionsModel> transactionsModelMap = new ConcurrentHashMap<>();

    @Override
    public TransactionsModel addTransaction(TransactionRequest transactionRequest) throws BillingPeriodNotFoundException {
        BillingPeriodIdModel billingPeriodForDate = billingPeriodService.getBillingPeriodForDate(transactionRequest.getDate());
        return transactionsModelMap.compute(transactionRequest.getAccountId() +
                billingPeriodForDate.getPeriodId(), (key, item) -> {
            if (item == null) {
                return TransactionsModel.builder()
                        .accountId(transactionRequest.getAccountId())
                        .billingPeriodId(billingPeriodForDate.getPeriodId())
                        .count(1)
                        .totalValue(transactionRequest.getValue())
                        .build();
            } else {
                return item.toBuilder()
                        .totalValue(item.getTotalValue() + transactionRequest.getValue())
                        .count(item.getCount() + 1)
                        .build();
            }
        });

    }
}
