package com.i.and.you.global.jwt.resolver;

import com.i.and.you.global.jwt.TokenProvider;
import com.i.and.you.global.jwt.annotation.JwtUserEmail;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtUserEmail.class); // JwtUserEmail 어노테이션이 붙어있는지 확인
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = Optional.ofNullable(webRequest.getNativeRequest(HttpServletRequest.class).getHeader(AUTHORIZATION_HEADER))
                .orElseThrow(() -> new IllegalArgumentException("Authorization 헤더가 존재하지 않습니다."));

        String accessToken = tokenProvider.getAccessToken(token);

        if (!tokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        return tokenProvider.getUserEmail(accessToken);
    }
}
