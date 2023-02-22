package com.billing.application.mapper;

import com.billing.application.dto.BillingPeriodsResponseDto;
import com.billing.domain.model.BillingPeriodModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillingPeriodResponseMapper {

    List<BillingPeriodsResponseDto> map(List<BillingPeriodModel> billingPeriodModels);
}
