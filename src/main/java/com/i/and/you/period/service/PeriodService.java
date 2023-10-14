package com.i.and.you.period.service;

import com.i.and.you.period.dto.PeriodResponse;

import java.time.LocalDate;

public interface PeriodService {
    PeriodResponse getPeriod(Long userId);
    long savePeriod(LocalDate startedAt, Long userId);
}
