package com.i.and.you.domain.plan.entity;

import com.i.and.you.domain.plan.dto.SavePlanRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Plan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Long userId;

    public static Plan createPlan(SavePlanRequest request) {
        return Plan.builder()
                .description(request.getDescription())
                .startDate(request.getStartedDate())
                .endDate(request.getEndedDate())
                .build();
    }
}
