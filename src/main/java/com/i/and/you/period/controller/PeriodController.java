package com.i.and.you.period.controller;


import com.i.and.you.period.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/period")
public class PeriodController {

    private final PeriodService periodService;

    @GetMapping
    public ResponseEntity<Long> getPeriod(LocalDate startedAt, long userId) {
        return ResponseEntity.ok()
                .body(periodService.getPeriod(startedAt, userId));
    }

}
