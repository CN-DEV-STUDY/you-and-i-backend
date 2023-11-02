package com.i.and.you.domain.notice.controller;

import com.i.and.you.domain.notice.dto.GetNoticeResponse;
import com.i.and.you.domain.notice.dto.SendRelationsNoticeRequest;
import com.i.and.you.domain.notice.exception.SseException;
import com.i.and.you.domain.notice.facade.NoticeFacade;
import com.i.and.you.global.api.ApiResult;
import com.i.and.you.global.jwt.annotation.JwtUserEmail;
import com.i.and.you.global.sse.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@RequestMapping("/notices")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final SseEmitters sseEmitters;
    private final NoticeFacade noticeFacade;

    @GetMapping(value = "/connect/{email}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable  String email) {
        SseEmitter sseEmitter = new SseEmitter(1000 * 60 * 30L); // 30분
        sseEmitters.put(email, sseEmitter);

        int unreadNoticeCount = noticeFacade.getUnreadNoticeCount(email);

        try {
            sseEmitter.send(SseEmitter.event()
                    .name("unreadNoticeCount")
                    .data(unreadNoticeCount));
        } catch (IOException e) {
            throw new SseException();
        }
        return ResponseEntity.ok(sseEmitter);
    }

    @PostMapping("/send")
    public void send(@RequestBody SendRelationsNoticeRequest request) {
        noticeFacade.sendRelationsNotice(request);
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<GetNoticeResponse>>> getNotices(@JwtUserEmail String email) {
        List<GetNoticeResponse> notices = noticeFacade.getNotices(email);
        return ApiResult.createSuccess(notices);
    }

    @PostMapping("/{noticeId}/accept")
    public ResponseEntity<ApiResult<Void>> acceptRelations(@JwtUserEmail String email, @PathVariable Long noticeId) {
        noticeFacade.acceptRelations(email, noticeId);

        return ApiResult.createSuccessWithNoContent();
    }

}
