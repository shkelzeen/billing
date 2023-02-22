package com.billing.domainservice;

import com.billing.domain.model.TransactionsModel;
import com.billing.domain.request.TransactionRequest;
import com.billing.exception.BillingPeriodNotFoundException;

public interface TransactionService {
    TransactionsModel addTransaction(TransactionRequest transactionRequest) throws BillingPeriodNotFoundException;
}
