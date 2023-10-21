package com.i.and.you.domain.period.service;

import com.i.and.you.domain.period.dto.PeriodResponse;

import java.time.LocalDate;

public interface PeriodService {
    PeriodResponse getPeriod(String email);
    long savePeriod(LocalDate startedAt, String email);
}
