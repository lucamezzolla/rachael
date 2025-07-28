package com.rachael.api.user.security;

import com.rachael.api.user.constant.ErrorMessages;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

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
        "/webjars/",
        "/api/user/login",
        "/api/user/register"
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
            chain.doFilter(request, response); // bypass security
            return;
        }

        String authHeader = httpReq.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessages.TOKEN_NOT_FOUND);
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessages.INVALID_TOKEN);
            return;
        }

        // Imposta l'autenticazione nel contesto di Spring Security
        String email = jwtUtil.extractEmail(token);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(email, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
