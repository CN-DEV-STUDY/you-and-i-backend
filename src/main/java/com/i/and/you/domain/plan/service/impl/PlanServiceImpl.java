package com.i.and.you.domain.plan.service.impl;

import com.i.and.you.domain.plan.dto.PlanResponse;
import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.repository.PlanRepository;
import com.i.and.you.domain.plan.service.PlanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Transactional
    @Override
    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }

    @Override
    public List<PlanResponse> getPlanBySearchDate(LocalDate searchDate, Long userId) {
        List<Plan> plans = planRepository.getPlanBySearchDate(searchDate, userId);

        return plans.stream().map(
                plan -> PlanResponse.builder()
                        .id(plan.getId())
                        .description(plan.getDescription())
                        .build())
                .toList();
    }

    @Override
    public Plan findById(Long planId) {
        return planRepository.findById(planId).orElseThrow(() -> new EntityNotFoundException("해당하는 계획이 없습니다."));
    }

    @Transactional
    @Override
    public void deleteById(Long planId) {
        planRepository.deleteById(planId);
    }
}
