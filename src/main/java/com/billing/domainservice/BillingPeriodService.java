package com.billing.domainservice;

import com.billing.domain.model.BillingPeriodIdModel;
import com.billing.domain.model.BillingPeriodModel;
import com.billing.exception.BillingPeriodNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface BillingPeriodService {
    BillingPeriodIdModel getBillingPeriodForDate(LocalDate localDate) throws BillingPeriodNotFoundException;

    List<BillingPeriodModel> getBillingPeriodsByYear(int year);
}
