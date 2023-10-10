package com.i.and.you.period.entity;

import com.i.and.you.common.entity.CreatedInfo;
import com.i.and.you.user.entity.User;
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
}
