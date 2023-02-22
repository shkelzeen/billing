package com.billing.dataaccess.mapper;

import com.billing.dataaccess.entity.bill.BillEntity;
import com.billing.domain.model.CreateBillResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillModelMapper {
    CreateBillResponseModel map(BillEntity billEntity);
}
