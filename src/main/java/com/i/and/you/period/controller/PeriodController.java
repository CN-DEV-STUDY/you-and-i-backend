package com.i.and.you.period.controller;


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
//    public ResponseEntity<PeriodResponse> getPeriod(Long userId) {
    public ResponseEntity<PeriodResponse> getPeriod() {
        return ResponseEntity.ok()
                .body(periodService.getPeriod(1L));
    }

    @PostMapping
    public ResponseEntity<Long> postPeriod(LocalDate startedAt) {
        return ResponseEntity.ok()
                .body(periodService.savePeriod(startedAt, 1L));
    }

}
