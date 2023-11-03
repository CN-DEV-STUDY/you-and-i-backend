package com.i.and.you.domain.plan.repository;

import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.repository.custom.PlanCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long>, PlanCustomRepository {
}
