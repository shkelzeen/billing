package com.billing.application.controller;

import com.billing.application.dto.BillingPeriodsResponseDto;
import com.billing.application.service.BillingPeriodApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/billing-period")
@Slf4j
@RequiredArgsConstructor
public class BillingPeriodController {
    private final BillingPeriodApplicationService billingPeriodApplicationService;

    @Operation(description = "Return billing periods for a given year",
            responses = {@ApiResponse(responseCode = "200", description = "Return billing periods for the year",
                    content = {@Content(schema = @Schema(implementation = BillingPeriodsResponseDto.class),
                            examples = {@ExampleObject(value = "[{ \"periodId\": \"2019-1\", \"from\":\"2019-01-01\",\"to\":\"2019-01-04\"}." +
                                    "{ \"periodId\":\"2019-2\", \"from\":\"2019-01-05\", \"to\":\"2019-01-11\"}")}
                    )})}
    )
    @GetMapping("{year}")
    public List<BillingPeriodsResponseDto> getBillingPeriodsByYear(@PathVariable("year") int year) {
        return billingPeriodApplicationService.getBillingPeriodByYear(year);
    }
}
