package com.i.and.you.common.jwt.controller;

import com.i.and.you.common.api.ApiResult;
import com.i.and.you.common.jwt.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final JwtService tokenService;

    @PostMapping("/access-token")
    public ResponseEntity<ApiResult<GenerateAccessTokenResponse>> generateAccessToken(@RequestBody GenerateAccessTokenRequest request) {
        String accessToken = tokenService.generateNewAccessToken(request.getRefreshToken());
        return ApiResult.createSuccess(new GenerateAccessTokenResponse(accessToken));
    }

    @Getter
    @AllArgsConstructor
    static class GenerateAccessTokenResponse {
        private String accessToken;
    }

    @Getter
    @Setter
    static class GenerateAccessTokenRequest {
        private String refreshToken;
    }
}
