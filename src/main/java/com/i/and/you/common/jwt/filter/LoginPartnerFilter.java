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
 * 컨트롤러 메서드의 DTO 파라미터에 partnerId값을 JWT에서 꺼내서 set해주는 필터
 */
@RequiredArgsConstructor
@Component
public class LoginPartnerFilter implements Filter {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");

        if (authorizationHeader == null) {
            chain.doFilter(request, response);
            return;
        }

        Long partnerId = tokenProvider.getPartnerId(tokenProvider.getAccessToken(authorizationHeader));

        PrettyFacesWrappedRequest mutableHttpRequest =
                new PrettyFacesWrappedRequest(
                        (HttpServletRequest) request,
                        Map.of("userId", new String[]{partnerId.toString()})
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
