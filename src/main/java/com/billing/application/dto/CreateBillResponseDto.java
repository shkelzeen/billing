package com.billing.application.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonDeserialize(builder = CreateBillResponseDto.CreateBillResponseDtoBuilder.class)
public class CreateBillResponseDto {
    private final Long id;
    private final String periodId;
    private final LocalDateTime billingDate;
}
