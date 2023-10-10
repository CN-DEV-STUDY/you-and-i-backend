package com.i.and.you.period.service;

import java.time.LocalDate;

public interface PeriodService {
    long getPeriod(LocalDate startedAt, Long userId);
}
