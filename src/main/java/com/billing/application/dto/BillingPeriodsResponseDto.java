package com.billing.application.dto;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonDeserialize(builder = BillingPeriodsResponseDto.BillingPeriodsResponseDtoBuilder.class)
public class BillingPeriodsResponseDto {
    private final String periodId;
    private final LocalDate from;
    private final LocalDate to;
}
