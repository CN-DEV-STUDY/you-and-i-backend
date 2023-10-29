package com.i.and.you.domain.user.dto;

public record SetRelationsRequest(
        String myEmail,
        String yourEmail
) {
}
