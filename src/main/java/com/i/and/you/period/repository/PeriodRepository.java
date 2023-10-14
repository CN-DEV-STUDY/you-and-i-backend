package com.i.and.you.period.repository;


import com.i.and.you.period.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodRepository extends JpaRepository<Period, Long> {

    Optional<Period> findByUserId(Long userId);
}
