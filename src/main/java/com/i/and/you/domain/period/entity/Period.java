package com.i.and.you.domain.period.entity;

import com.i.and.you.global.entity.CreatedInfo;
import com.i.and.you.domain.period.dto.PeriodResponse;
import com.i.and.you.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Period extends CreatedInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "period_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate startedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Period createPeriod(LocalDate startedAt, User user) {
        return Period.builder()
                .startedAt(startedAt)
                .user(user)
                .build();
    }

    public PeriodResponse toDto(Long period, LocalDate startedAt) {
        return PeriodResponse.builder()
                .period(period)
                .startedAt(startedAt)
                .build();
    }
}
