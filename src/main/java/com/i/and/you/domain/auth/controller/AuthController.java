package com.i.and.you.domain.auth.controller;

import com.i.and.you.domain.auth.dto.LoginRequest;
import com.i.and.you.domain.auth.dto.LoginResponse;
import com.i.and.you.domain.auth.dto.LogoutRequest;
import com.i.and.you.domain.auth.facade.AuthFacade;
import com.i.and.you.global.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<ApiResult<LoginResponse>> login(@RequestBody LoginRequest request) {
        return ApiResult.createSuccess(authFacade.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResult<Void>> logout(LogoutRequest request) {
        authFacade.logout(request);
        return ApiResult.createSuccessWithNoContent();
    }
}
