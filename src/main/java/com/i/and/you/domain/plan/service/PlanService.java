package com.i.and.you.domain.plan.service;

import com.i.and.you.domain.plan.dto.PlanResponse;
import com.i.and.you.domain.plan.entity.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    void savePlan(Plan plan);

    List<PlanResponse> getPlanBySearchDate(LocalDate searchDate, Long userId);
}
