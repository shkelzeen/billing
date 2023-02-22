package com.billing.application.mapper;

import com.billing.application.dto.BillingPeriodsResponseDto;
import com.billing.domain.model.BillingPeriodModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BillingPeriodResponseMapperTest {

    private BillingPeriodResponseMapper billingPeriodResponseMapper = new BillingPeriodResponseMapperImpl();

    @Test
    public void testMap() {
        BillingPeriodModel billingPeriodModel = BillingPeriodModel.builder()
                .periodId("2019-1")
                .from(LocalDate.now().minusWeeks(1))
                .to(LocalDate.now())
                .build();
        List<BillingPeriodsResponseDto> billingPeriodsResponseDtos = billingPeriodResponseMapper.map(List.of(billingPeriodModel));

        assertNotNull(billingPeriodsResponseDtos);
        assertEquals(1, billingPeriodsResponseDtos.size());
        assertEquals("2019-1", billingPeriodsResponseDtos.get(0).getPeriodId());
    }
}
