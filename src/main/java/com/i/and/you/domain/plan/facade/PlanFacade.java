package com.i.and.you.domain.plan.facade;

import com.i.and.you.domain.plan.dto.SavePlanRequest;
import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.service.PlanService;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanFacade {
    private final PlanService planService;
    private final UserService userService;

    public void savePlan(SavePlanRequest request, String email) {
        User user = userService.findByEmail(email);
        planService.savePlan(request.createPlan(request, user.getId()));
    }
}
