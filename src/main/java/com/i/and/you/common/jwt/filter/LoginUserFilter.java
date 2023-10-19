package com.i.and.you.common.jwt.filter;

import com.i.and.you.common.jwt.TokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


/**
 * https://www.ocpsoft.org/opensource/how-to-safely-add-modify-servlet-request-parameter-values/
 *
 * 컨트롤러 메서드의 DTO 파라미터에 user email값을 JWT에서 꺼내서 set해주는 필터
 */
@RequiredArgsConstructor
@Component
public class LoginUserFilter implements Filter {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader("Authorization");

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        String email = tokenProvider.getUserEmail(tokenProvider.getAccessToken(token));

        PrettyFacesWrappedRequest mutableHttpRequest =
                new PrettyFacesWrappedRequest(
                        (HttpServletRequest) request,
                        Map.of("email", new String[]{email})
                );

        chain.doFilter(mutableHttpRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
