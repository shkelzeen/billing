package com.billing.application.service;

import com.billing.application.dto.BillingPeriodsResponseDto;
import com.billing.domainservice.BillingPeriodService;
import com.billing.application.mapper.BillingPeriodResponseMapper;
import com.billing.domain.model.BillingPeriodModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BillingPeriodApplicationService {

    private final BillingPeriodService billingPeriodService;
    private final BillingPeriodResponseMapper billingPeriodResponseMapper;

    public List<BillingPeriodsResponseDto> getBillingPeriodByYear(int year) {
        List<BillingPeriodModel> billingPeriodsByYear = billingPeriodService.getBillingPeriodsByYear(year);
        return billingPeriodResponseMapper.map(billingPeriodsByYear);
    }
}
