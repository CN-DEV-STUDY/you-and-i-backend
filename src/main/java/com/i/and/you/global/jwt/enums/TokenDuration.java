package com.i.and.you.global.jwt.enums;

import lombok.Getter;

import java.time.Duration;

@Getter
public enum TokenDuration {
    ACCESS_TOKEN_DURATION(Duration.ofMinutes(30)),
    REFRESH_TOKEN_DURATION(Duration.ofDays(7));

    private final Duration duration;

    TokenDuration(Duration duration) {
        this.duration = duration;
    }
}
