package com.i.and.you.domain.user.dto;

import com.i.and.you.domain.user.entity.User;

public record SaveUserRequest(
        String email,
        String name,
        String nickname,
        String password
) {

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
