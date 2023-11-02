package com.i.and.you.domain.notice.dto;

import com.i.and.you.domain.notice.entity.Notice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GetNoticeResponse {
    private Long noticeId;
    private String title;
    private String content;
    private String senderNickname;
    private String senderEmail;
    private LocalDateTime sentAt;

    public GetNoticeResponse(Notice notice) {
        this.noticeId = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getTitle();
        this.senderNickname = notice.getSender().getNickname();
        this.senderEmail = notice.getSender().getEmail();
        this.sentAt = notice.getSentAt();
    }

    public static List<GetNoticeResponse> entityToDto(List<Notice> notices) {
        return notices.stream()
                .map(GetNoticeResponse::new)
                .collect(Collectors.toList());
    }
}
