package com.billing.application.mapper;

import com.billing.application.dto.CreateBillResponseDto;
import com.billing.domain.model.CreateBillResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateBillResponseDtoMapper {

    CreateBillResponseDto map(CreateBillResponseModel createBillResponseModel);
}
