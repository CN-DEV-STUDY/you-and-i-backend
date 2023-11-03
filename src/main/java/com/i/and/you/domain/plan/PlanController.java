package com.i.and.you.domain.plan;

import com.i.and.you.domain.plan.dto.SavePlanRequest;
import com.i.and.you.domain.plan.facade.PlanFacade;
import com.i.and.you.global.api.ApiResult;
import com.i.and.you.global.jwt.annotation.JwtUserEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans")
public class PlanController {
    private final PlanFacade planFacade;

    @PostMapping
    public ResponseEntity<ApiResult<Void>> savePlan(@RequestBody SavePlanRequest request, @JwtUserEmail String email) {
        planFacade.savePlan(request, email);
        return ApiResult.createSuccessWithNoContent();
    }
}
