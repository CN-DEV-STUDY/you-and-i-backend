package com.i.and.you.user.dto;

/**
 * 회원가입 시 상대방 찾기 응답
 */
public record FindUserResponse (
        Long id,
        String name,
        String nickname
) {
}
