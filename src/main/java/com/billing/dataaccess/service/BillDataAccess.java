package com.billing.dataaccess.service;

import com.billing.domain.model.CreateBillResponseModel;
import com.billing.domain.request.CreateBillRequest;

public interface BillDataAccess {
    CreateBillResponseModel createBill(CreateBillRequest createBillRequest);
}
