package com.i.and.you.user.dto;

import org.springframework.util.StringUtils;

/**
 * 회원가입 시 상대방 찾기 요청
 */
public record FindUserRequest(
        String name,
        String nickname,
        String email
) {

    public boolean hasName() {
        return StringUtils.hasText(name);
    }

    public boolean hasNickname() {
        return StringUtils.hasText(nickname);
    }

    public boolean hasEmail() {
        return StringUtils.hasText(email);
    }
}
