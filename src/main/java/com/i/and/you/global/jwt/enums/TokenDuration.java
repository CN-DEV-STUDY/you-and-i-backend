package com.i.and.you.global.jwt.enums;

import lombok.Getter;

import java.time.Duration;

@Getter
public enum TokenDuration {
    ACCESS_TOKEN_DURATION(Duration.ofMinutes(30)),
    REFRESH_TOKEN_DURATION(Duration.ofDays(7)),
    REMEMBER_ME_ACCESS_TOKEN_DURATION(Duration.ofHours(365 * 24 * 10)), // 10년
    REMEMBER_ME_REFRESH_TOKEN_DURATION(Duration.ofDays(365 * 24 * 10)); // 10년

    private final Duration duration;

    TokenDuration(Duration duration) {
        this.duration = duration;
    }
}
