package com.i.and.you.domain.user.dto;

import com.i.and.you.domain.user.entity.User;
import com.i.and.you.global.api.Pagination;

import java.util.List;

/**
 * 회원가입 시 상대방 찾기 응답
 */
public record FindUserResponse (
        Long id,
        String name,
        String nickname,
        String email
) {

    public FindUserResponse(Long id, String name, String nickname, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public FindUserResponse(User user) {
        this(user.getId(), user.getName(), user.getNickname(), user.getEmail());
    }

    public static List<FindUserResponse> entityToDto(List<User> users) {
        return users.stream()
                .map(FindUserResponse::new)
                .toList();
    }
}
