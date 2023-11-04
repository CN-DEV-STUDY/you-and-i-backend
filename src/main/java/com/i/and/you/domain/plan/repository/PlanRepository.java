package com.i.and.you.domain.plan.repository;

import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.repository.custom.PlanCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Long>, PlanCustomRepository {
    Optional<Plan> findById(Long planId);
    void deleteById(Long planId);
}
