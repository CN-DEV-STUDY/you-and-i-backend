package com.i.and.you.user.dto;

import com.i.and.you.user.entity.User;
import lombok.Getter;

public record AddUserRequest(
        String email,
        String name,
        String nickname,
        String password,
        Long youId
) {

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .build();
    }

    public boolean hasYou() {
        return youId != null;
    }
}
