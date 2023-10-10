package com.i.and.you.period.service.impl;

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
@Transactional
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final UserRepository userRepository;

    @Override
    public long getPeriod(LocalDate startedAt, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        periodRepository.save(Period.createPeriod(startedAt, user));


        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(startedAt, today);
    }
}
