package com.i.and.you.domain.period.service.impl;

import com.i.and.you.domain.period.dto.PeriodResponse;
import com.i.and.you.domain.period.repository.PeriodRepository;
import com.i.and.you.domain.period.service.PeriodService;
import com.i.and.you.domain.period.entity.Period;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.repository.UserRepository;
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
    public PeriodResponse getPeriod(String email) {
        User user = findUserByEmail(email);

        Period period = periodRepository.findByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("Period not found"));
        LocalDate today = LocalDate.now();
        long diff = ChronoUnit.DAYS.between(period.getStartedAt(), today);

        return period.toDto(diff, period.getStartedAt());
    }

    @Transactional
    @Override
    public long savePeriod(LocalDate startedAt, String email) {
        User user = findUserByEmail(email);
        periodRepository.save(Period.createPeriod(startedAt, user));

        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(startedAt, today);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
