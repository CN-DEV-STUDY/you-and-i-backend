package com.i.and.you.domain.user.dto;

/**
 * 회원가입 시 상대방 찾기 응답
 */
public record FindUserResponse (
        Long id,
        String name,
        String nickname
) {
}
