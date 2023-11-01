package com.i.and.you.domain.notice.dto;

public record SendRelationsNoticeRequest(
        String sender,
        String receiver
) {
}
