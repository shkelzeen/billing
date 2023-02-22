package com.billing.domainservice;

import com.billing.domain.model.TransactionsModel;
import com.billing.domain.request.TransactionRequest;
import com.billing.exception.BillingPeriodNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testAddTransaction() throws BillingPeriodNotFoundException {
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .accountId("1")
                .value(123.45)
                .date(LocalDate.of(2019, 01, 01))
                .build();

        TransactionsModel transactionsModel = transactionService.addTransaction(transactionRequest);

        assertNotNull(transactionsModel);
        assertEquals(transactionRequest.getAccountId(), transactionsModel.getAccountId());
        assertEquals(transactionRequest.getValue(), transactionsModel.getTotalValue());
        assertEquals(1, transactionsModel.getCount());
        assertEquals("2019-1", transactionsModel.getBillingPeriodId());

        TransactionRequest transactionRequest2 = TransactionRequest.builder()
                .accountId("1")
                .value(-100.12)
                .date(LocalDate.of(2019, 01, 03))
                .build();

        transactionsModel = transactionService.addTransaction(transactionRequest2);

        assertNotNull(transactionsModel);
        assertEquals(transactionRequest.getAccountId(), transactionsModel.getAccountId());
        assertEquals(transactionRequest.getValue() + transactionRequest2.getValue(), transactionsModel.getTotalValue());
        assertEquals(2, transactionsModel.getCount());
        assertEquals("2019-1", transactionsModel.getBillingPeriodId());
    }

}
