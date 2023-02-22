package com.billing.dataaccess.service.impl;

import com.billing.dataaccess.repository.BillRepository;
import com.billing.dataaccess.entity.bill.BillEntity;
import com.billing.dataaccess.mapper.BillEntityMapper;
import com.billing.dataaccess.mapper.BillModelMapper;
import com.billing.dataaccess.service.BillDataAccess;
import com.billing.domain.model.CreateBillResponseModel;
import com.billing.domain.request.CreateBillRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillDataAccessImpl implements BillDataAccess {

    private final BillEntityMapper billEntityMapper;
    private final BillModelMapper billModelMapper;
    private final BillRepository billRepository;

    @Override
    public CreateBillResponseModel createBill(CreateBillRequest createBillRequest) {
        BillEntity billEntity = billEntityMapper.map(createBillRequest);
        billEntity = billRepository.save(billEntity);
        return billModelMapper.map(billEntity);
    }
}
