package com.i.and.you.domain.plan.repository.custom;

import com.i.and.you.domain.plan.entity.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanCustomRepository {
    List<Plan> getPlanBySearchDate(LocalDate searchDate, Long userId);
}
