package com.i.and.you.domain.plan.service.impl;

import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.repository.PlanRepository;
import com.i.and.you.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }
}
