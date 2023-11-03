package com.i.and.you.domain.plan.controller;

import com.i.and.you.domain.plan.dto.PlanRequest;
import com.i.and.you.domain.plan.dto.PlanResponse;
import com.i.and.you.domain.plan.facade.PlanFacade;
import com.i.and.you.global.api.ApiResult;
import com.i.and.you.global.jwt.annotation.JwtUserEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans")
public class PlanController {
    private final PlanFacade planFacade;

    @PostMapping
    public ResponseEntity<ApiResult<Void>> savePlan(@RequestBody PlanRequest request, @JwtUserEmail String email) {
        planFacade.savePlan(request, email);
        return ApiResult.createSuccessWithNoContent();
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<PlanResponse>>> getPlan(@RequestParam LocalDate searchDate, @JwtUserEmail String email) {
        List<PlanResponse> plan = planFacade.getPlanBySearchDate(searchDate, email);
        return ApiResult.createSuccess(plan);
    }
}
