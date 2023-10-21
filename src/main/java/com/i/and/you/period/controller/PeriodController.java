package com.i.and.you.period.controller;


import com.i.and.you.common.jwt.annotation.JwtUserEmail;
import com.i.and.you.period.dto.PeriodResponse;
import com.i.and.you.period.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/period")
public class PeriodController {

    private final PeriodService periodService;

    @GetMapping
    public ResponseEntity<PeriodResponse> getPeriod(@JwtUserEmail String email) {
        return ResponseEntity.ok()
                .body(periodService.getPeriod(email));
    }

    @PostMapping
    public ResponseEntity<Long> postPeriod(LocalDate startedAt, @JwtUserEmail String email) {
        return ResponseEntity.ok()
                .body(periodService.savePeriod(startedAt, email));
    }

}
