package com.billing.dataaccess.mapper;

import com.billing.dataaccess.entity.bill.BillEntity;
import com.billing.domain.request.CreateBillRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillEntityMapper {
    BillEntity map(CreateBillRequest createBillRequest);
}
