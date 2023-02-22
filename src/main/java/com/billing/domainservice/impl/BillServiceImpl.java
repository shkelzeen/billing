package com.billing.domainservice.impl;

import com.billing.dataaccess.service.BillDataAccess;
import com.billing.domain.model.BillingPeriodIdModel;
import com.billing.domain.model.CreateBillResponseModel;
import com.billing.domain.request.CreateBillRequest;
import com.billing.domainservice.BillService;
import com.billing.domainservice.BillingPeriodService;
import com.billing.exception.BillingPeriodNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {

    private final BillDataAccess billDataAccess;
    private final BillingPeriodService billingPeriodService;

    @Override
    public CreateBillResponseModel createBill(CreateBillRequest createBillRequest) {
        LocalDateTime localDateTime = LocalDateTime.now();
        BillingPeriodIdModel billingPeriodForDate = null;
        try {
            billingPeriodForDate = billingPeriodService.getBillingPeriodForDate(localDateTime.toLocalDate());
        } catch (BillingPeriodNotFoundException e) {
            log.warn(e.getMessage());
        }
        createBillRequest = createBillRequest.toBuilder()
                .billingDate(localDateTime)
                .billingPeriodId(billingPeriodForDate != null ? billingPeriodForDate.getPeriodId() : null)
                .build();

        return billDataAccess.createBill(createBillRequest);
    }
}
