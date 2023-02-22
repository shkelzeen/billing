package com.billing.application.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;


//This Dto should contain information regarding transaction, bill amount, name and so on...
@Getter
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonDeserialize(builder = CreateBillRequestDto.CreateBillRequestDtoBuilder.class)
public class CreateBillRequestDto {
}
