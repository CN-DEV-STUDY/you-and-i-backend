package com.i.and.you.global.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SseEmitters {

    private final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    public void put(String key, SseEmitter sseEmitter) {
        sseEmitterMap.put(key, sseEmitter);
        log.info("new emitter added: {}", sseEmitter);
        log.info("current emitters: {}", sseEmitterMap.size());

        sseEmitter.onCompletion(() -> sseEmitterMap.remove(key));
        sseEmitter.onTimeout(() -> sseEmitterMap.get(key).complete());
    }

    public void incrementUnreadNoticeCount(String email, int unreadNoticeCount) {
        SseEmitter sseEmitter = sseEmitterMap.get(email);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(SseEmitter.event()
                        .name("unreadNoticeCount")
                        .data(unreadNoticeCount));
            } catch (Exception e) {
                log.error("error while sending notification: {}", e.getMessage());
            }
        }
    }
}
