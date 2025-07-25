package com.rachael.api.wallet.security;

import com.rachael.api.wallet.constant.ErrorMessages;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter implements Filter {

    private static final List<String> WHITELIST = List.of(
        "/v3/api-docs",
        "/v3/api-docs/",
        "/v3/api-docs/swagger-config",
        "/swagger-ui",
        "/swagger-ui/",
        "/swagger-ui.html",
        "/swagger-ui/index.html",
        "/swagger-resources",
        "/swagger-resources/",
        "/webjars/"
    );

    private boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::startsWith);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String path = httpReq.getServletPath();

        if (isWhitelisted(path)) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpReq.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessages.TOKEN_NOT_FOUND);
            return;
        }

        // Token presente: lo accettiamo come valido (la validazione è demandata a un altro servizio)
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("external-user", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
