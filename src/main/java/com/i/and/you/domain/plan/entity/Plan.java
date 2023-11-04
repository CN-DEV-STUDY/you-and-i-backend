package com.i.and.you.domain.plan.entity;

import com.i.and.you.domain.plan.dto.PlanRequest;
import com.i.and.you.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Plan extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Long userId;

    public static Plan createPlan(PlanRequest request) {
        return Plan.builder()
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    public void updatePlan(PlanRequest.Update request) {
        this.description = request.getDescription() != null ? request.getDescription() : this.description;
        this.startDate = request.getStartDate() != null ? request.getStartDate() : this.startDate;
        this.endDate = request.getEndDate() != null ? request.getEndDate() : this.endDate;
    }
}
