package com.billing.domainservice;

import com.billing.domainservice.impl.BillingPeriodServiceImpl;
import com.billing.domain.model.BillingPeriodIdModel;
import com.billing.domain.model.BillingPeriodModel;
import com.billing.exception.BillingPeriodNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BillingPeriodServiceTest {

    private BillingPeriodService billingPeriodService = new BillingPeriodServiceImpl();

    @Test
    public void testGetBillingPeriodForDate() throws BillingPeriodNotFoundException {
        LocalDate localDate = LocalDate.now().minusYears(2);
        BillingPeriodIdModel billingPeriodForDate = billingPeriodService.getBillingPeriodForDate(localDate);

        assertNotNull(billingPeriodForDate);
    }

    @Test
    public void testGetBillingPeriodsByYear() {
        List<BillingPeriodModel> billingPeriodsByYear = billingPeriodService.getBillingPeriodsByYear(2019);

        assertNotNull(billingPeriodsByYear);
        assertEquals("2019-1", billingPeriodsByYear.get(0).getPeriodId());
        assertEquals(LocalDate.of(2019, 1, 1), billingPeriodsByYear.get(0).getFrom());
        assertEquals(LocalDate.of(2019, 12, 31), billingPeriodsByYear.get(billingPeriodsByYear.size() - 1).getTo());

    }
}
