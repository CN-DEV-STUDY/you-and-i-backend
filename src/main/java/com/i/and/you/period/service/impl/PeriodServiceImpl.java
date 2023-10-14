package com.i.and.you.period.service.impl;

import com.i.and.you.period.dto.PeriodResponse;
import com.i.and.you.period.entity.Period;
import com.i.and.you.period.repository.PeriodRepository;
import com.i.and.you.period.service.PeriodService;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final UserRepository userRepository;

    @Override
    public PeriodResponse getPeriod(Long userId) {

        Period period = periodRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Period not found"));
        LocalDate today = LocalDate.now();
        long diff = ChronoUnit.DAYS.between(period.getStartedAt(), today);

        return period.toDto(diff, period.getStartedAt());
    }

    @Transactional
    @Override
    public long savePeriod(LocalDate startedAt, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        periodRepository.save(Period.createPeriod(startedAt, user));


        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(startedAt, today);
    }
}
