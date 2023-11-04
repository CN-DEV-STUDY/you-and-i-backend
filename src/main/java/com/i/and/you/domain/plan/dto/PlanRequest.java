package com.i.and.you.domain.plan.dto;

import com.i.and.you.domain.plan.entity.Plan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlanRequest {
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Plan createPlan(PlanRequest request, Long userId) {
        return Plan.builder()
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .userId(userId)
                .build();
    }


    @Getter
    @Setter
    public static class Update extends PlanRequest {
        private Long planId;
    }
}
