package com.i.and.you.domain.period.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PeriodResponse {
    private Long period;
    private LocalDate startedAt;
}
