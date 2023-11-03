package com.i.and.you.domain.plan.dto;

import com.i.and.you.domain.plan.entity.Plan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SavePlanRequest {
    private String description;
    private LocalDate startedDate;
    private LocalDate endedDate;

    public Plan createPlan(SavePlanRequest request, Long userId) {
        return Plan.builder()
                .description(request.getDescription())
                .startDate(request.getStartedDate())
                .endDate(request.getEndedDate())
                .userId(userId)
                .build();
    }
}
