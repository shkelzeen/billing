package com.billing.application.service;

import com.billing.application.dto.CreateBillRequestDto;
import com.billing.application.dto.CreateBillResponseDto;
import com.billing.domainservice.BillService;
import com.billing.application.mapper.CreateBillRequestDtoMapper;
import com.billing.application.mapper.CreateBillResponseDtoMapper;
import com.billing.domain.request.CreateBillRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillApplicationService {

    private final BillService billService;
    private final CreateBillRequestDtoMapper billingDtoMapper;
    private final CreateBillResponseDtoMapper createBillResponseDtoMapper;

    public CreateBillResponseDto createBill(CreateBillRequestDto createBillRequestDto) {
        CreateBillRequest createBillRequest = billingDtoMapper.map(createBillRequestDto);

        return createBillResponseDtoMapper.map(billService.createBill(createBillRequest));
    }
}
