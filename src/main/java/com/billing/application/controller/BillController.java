package com.billing.application.controller;


import com.billing.application.dto.CreateBillRequestDto;
import com.billing.application.dto.CreateBillResponseDto;
import com.billing.application.service.BillApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bill")
@Slf4j
@RequiredArgsConstructor
public class BillController {

    private final BillApplicationService billApplicationService;

    @Operation(description = "Create billing ",
            responses = {@ApiResponse(responseCode = "200", description = "Created bill with details",
                    content = {@Content(schema = @Schema(implementation = CreateBillResponseDto.class),
                            examples = {@ExampleObject(value = "{ \"id\": \"uuid\",  \"billingDate\": " +
                                    "\"01.01.2023 12:00\", \"billingPeriod\": \"1-2023\", }")}
                    )})}
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateBillResponseDto createBill(@RequestBody CreateBillRequestDto createBillDto) {
        return billApplicationService.createBill(createBillDto);
    }
}
