package com.i.and.you.domain.notice.facade;

import com.i.and.you.domain.notice.dto.SendRelationsNoticeRequest;
import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.notice.service.NoticeService;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
import com.i.and.you.global.sse.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.i.and.you.domain.notice.entity.Notice.*;

@RequiredArgsConstructor
@Service
public class NoticeFacade {

    private final NoticeService noticeService;
    private final UserService userService;
    private final SseEmitters sseEmitters;

    public int getUnreadNoticeCount(String email) {
        User user = userService.findByEmail(email);
        return noticeService.getUnreadNoticeCount(user);
    }

    public void sendRelationsNotice(SendRelationsNoticeRequest request) {
        User sender = userService.findByEmail(request.sender());
        User receiver = userService.findByEmail(request.receiver());

        noticeService.saveRelationsNotice(createRelationsNotice(sender, receiver));

        int unreadNoticeCount = getUnreadNoticeCount(request.receiver());
        sseEmitters.incrementUnreadNoticeCount(request.receiver(), unreadNoticeCount);
    }
}
