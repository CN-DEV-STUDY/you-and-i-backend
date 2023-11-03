package com.i.and.you.domain.plan.repository.custom.impl;

import com.i.and.you.domain.plan.entity.Plan;
import com.i.and.you.domain.plan.entity.QPlan;
import com.i.and.you.domain.plan.repository.custom.PlanCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.i.and.you.domain.plan.entity.QPlan.plan;

@Repository
@RequiredArgsConstructor
public class PlanCustomRepositoryImpl implements PlanCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Plan> getPlanBySearchDate(LocalDate searchDate, Long userId) {
        return queryFactory.selectFrom(plan)
                .where(plan.userId.eq(userId)
                        .and(plan.startDate.loe(searchDate))
                        .and(plan.endDate.goe(searchDate)))
                .orderBy(plan.createdAt.desc())
                .fetch();
    }
}
