package com.i.and.you.domain.test.controller;

import com.i.and.you.global.sse.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final SseEmitters sseEmitters;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
      return user;
    }

}
