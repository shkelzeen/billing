package com.billing.domainservice.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.billing.domain.model.BillingPeriodIdModel;
import com.billing.domain.model.BillingPeriodModel;
import com.billing.domainservice.BillingPeriodService;
import com.billing.exception.BillingPeriodNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingPeriodServiceImpl implements BillingPeriodService {
    private final Cache<LocalDate, BillingPeriodIdModel> billingPeriodIdModelCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.HOURS)
            .build();
    private final Cache<Integer, List<BillingPeriodModel>> billingPeriodModelCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.HOURS)
            .build();

    @Override
    public BillingPeriodIdModel getBillingPeriodForDate(LocalDate localDate) throws BillingPeriodNotFoundException {
        try {
            return billingPeriodIdModelCache.get(localDate, () -> {
                try {
                    return getBillingPeriodIdModel(localDate);
                } catch (BillingPeriodNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (ExecutionException | RuntimeException e) {
            log.warn("Unable to get the BillPeriodIdModel from cache, BillPeriodIdModel will be created");
            return getBillingPeriodIdModel(localDate);
        }
    }

    @Override
    public List<BillingPeriodModel> getBillingPeriodsByYear(int year) {
        try {
            return billingPeriodModelCache.get(year, () -> generateBillingPeriodsByYear(year));
        } catch (ExecutionException e) {
            log.warn("Unable to get the BillingPeriodsByYear from cache, BillingPeriodsByYear will be created");
            return generateBillingPeriodsByYear(year);
        }
    }

    private List<BillingPeriodModel> generateBillingPeriodsByYear(int year) {
        List<BillingPeriodModel> billPeriodModels = new ArrayList<>();

        LocalDateTime currentDay = LocalDateTime.of(year, 01, 1, 00, 00);
        int weekNumber = 1;
        do {
            LocalDateTime endOfWeek = endOfWeek(currentDay);
            if (belongsToNextMonthOrYear(currentDay, endOfWeek)) {
                endOfWeek = endOfMonth(currentDay);
            }
            billPeriodModels.add(getBillingPeriodModel(currentDay.toLocalDate(), endOfWeek.toLocalDate(), weekNumber++));
            currentDay = endOfWeek.plusDays(1);
        } while (currentDay.getYear() == year);

        return billPeriodModels;
    }

    private boolean belongsToNextMonthOrYear(LocalDateTime currentDay, LocalDateTime endOfWeek) {
        return currentDay.getMonthValue() < endOfWeek.getMonthValue()
                || endOfWeek.getYear() > currentDay.getYear();
    }

    private BillingPeriodModel getBillingPeriodModel(LocalDate firstDayOfWeek,
                                                     LocalDate lastDayOfWeek,
                                                     int weekNumber) {
        return BillingPeriodModel.builder()
                .periodId(String.format("%d-%d", lastDayOfWeek.getYear(), weekNumber))
                .from(firstDayOfWeek)
                .to(lastDayOfWeek)
                .build();
    }

    private LocalDateTime endOfWeek(LocalDateTime localDate) {
        return localDate
                .with(LocalTime.MAX)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

    }

    private LocalDateTime endOfMonth(LocalDateTime localDateTime) {
        return localDateTime
                .with(LocalTime.MAX)
                .with(TemporalAdjusters.lastDayOfMonth());
    }

    private BillingPeriodIdModel getBillingPeriodIdModel(LocalDate localDate) throws BillingPeriodNotFoundException {
        List<BillingPeriodModel> billingPeriodsByYear = getBillingPeriodsByYear(localDate.getYear());
        BillingPeriodIdModel billingPeriodIdModel = null;
        for (BillingPeriodModel billingPeriodModel : billingPeriodsByYear) {
            if (belongsToBillingPeriod(billingPeriodModel, localDate)) {
                billingPeriodIdModel = BillingPeriodIdModel.builder()
                        .periodId(billingPeriodModel.getPeriodId())
                        .build();
            }
        }
        if (billingPeriodIdModel == null) {
            throw new BillingPeriodNotFoundException(String.format("Billing period not found for date %s", localDate));
        } else {
            return billingPeriodIdModel;
        }
    }

    private boolean belongsToBillingPeriod(BillingPeriodModel billingPeriodModel,
                                           LocalDate localDate) {
        return (localDate.isAfter(billingPeriodModel.getFrom()) || billingPeriodModel.getFrom().equals(localDate))
                && (localDate.isBefore(billingPeriodModel.getTo()) || localDate.equals(billingPeriodModel.getTo()));
    }
}
