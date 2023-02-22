package com.billing.domainservice;


import com.billing.domain.model.CreateBillResponseModel;
import com.billing.domain.request.CreateBillRequest;

public interface BillService {
    CreateBillResponseModel createBill(CreateBillRequest createBillRequest);
}
