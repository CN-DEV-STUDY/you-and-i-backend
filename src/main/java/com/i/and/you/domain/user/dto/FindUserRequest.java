package com.i.and.you.domain.user.dto;

import com.i.and.you.domain.user.enums.UserSearchType;

/**
 * 회원가입 시 상대방 찾기 요청
 */
public record FindUserRequest(
        UserSearchType searchType,
        String searchWord

) {
}
