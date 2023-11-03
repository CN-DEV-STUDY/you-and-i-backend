package com.i.and.you.domain.plan.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanResponse {
    private Long id;
    private String description;
}
