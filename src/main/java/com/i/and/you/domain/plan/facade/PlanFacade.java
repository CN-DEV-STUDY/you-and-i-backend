package com.i.and.you.domain.plan.facade;

import com.i.and.you.domain.plan.dto.PlanRequest;
import com.i.and.you.domain.plan.dto.PlanResponse;
import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.service.PlanService;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanFacade {
    private final PlanService planService;
    private final UserService userService;

    public void savePlan(PlanRequest request, String email) {
        User user = userService.findByEmail(email);
        planService.savePlan(request.createPlan(request, user.getId()));
    }

    public List<PlanResponse> getPlanBySearchDate(LocalDate searchDate, String email) {
        User user = userService.findByEmail(email);
        return planService.getPlanBySearchDate(searchDate, user.getId());
    }

    public void updatePlan(PlanRequest.Update request) {
        Plan plan = planService.findById(request.getPlanId());
        plan.updatePlan(request);
    }

    public void deletePlan(Long planId) {
        planService.deleteById(planId);
    }

}
