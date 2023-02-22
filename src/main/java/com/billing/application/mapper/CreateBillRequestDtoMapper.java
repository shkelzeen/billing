package com.billing.application.mapper;

import com.billing.application.dto.CreateBillRequestDto;
import com.billing.domain.request.CreateBillRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateBillRequestDtoMapper {

    CreateBillRequest map(CreateBillRequestDto createBillDto);
}
