package com.i.and.you.domain.notice.facade;

import com.i.and.you.domain.chat.service.ChatService;
import com.i.and.you.domain.notice.dto.GetNoticeResponse;
import com.i.and.you.domain.notice.dto.SendRelationsNoticeRequest;
import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.notice.exception.NotMyNoticeException;
import com.i.and.you.domain.notice.service.NoticeService;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
import com.i.and.you.global.sse.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.i.and.you.domain.notice.dto.GetNoticeResponse.*;
import static com.i.and.you.domain.notice.entity.Notice.*;

@RequiredArgsConstructor
@Service
public class NoticeFacade {

    private final NoticeService noticeService;
    private final UserService userService;
    private final SseEmitters sseEmitters;
    private final ChatService chatService;

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

    @Transactional
    public List<GetNoticeResponse> getNotices(String email) {
        User user = userService.findByEmail(email);
        return entityToDto(noticeService.getNotices(user));
    }

    @Transactional
    public void acceptRelations(String email, Long noticeId) {
        User me = userService.findByEmail(email);
        Notice notice = noticeService.findById(noticeId);

        if (!notice.isMyNotice(me)) {
            throw new NotMyNoticeException();
        }

        setRelations(me, notice.getSender());
        putThemInTheSameChatRoom(me, notice.getSender());
        noticeService.delete(notice);
    }

    /**
     * 채팅방 생성해서 같은 채팅으로 업데이트
     */
    private void putThemInTheSameChatRoom(User me, User you) {
        String chatRoomId = chatService.generateChatRoomId();
        me.updateChatRoom(chatRoomId);
        you.updateChatRoom(chatRoomId);
    }

    /**
     * 유앤아이 신청한 사람과 연관관계 맺기
     */
    private static void setRelations(User me, User you) {
        me.pairUpWith(you);
    }
}
