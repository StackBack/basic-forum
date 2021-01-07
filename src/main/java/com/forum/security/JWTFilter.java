package com.forum.security;

import com.forum.service.impl.TokenServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

@Configuration
public class JWTFilter extends GenericFilterBean {
    private static final String AUTHORIZATION = "Authorization";
    private static final String USER_ID = "userId";
    private static final String OPTIONS_REQUEST_METHOD = "OPTIONS";

    private final TokenServiceImpl tokenService;

    public JWTFilter(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = getJwtFromRequest((HttpServletRequest) req);

        if (OPTIONS_REQUEST_METHOD.equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        if (allowRequestWithoutToken(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {
            if (isNull(token) || !tokenService.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                Long userId = tokenService.getUserIdFromToken(token);
                request.setAttribute(USER_ID, userId);
                filterChain.doFilter(req, res);
            }
        }

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }

    public boolean allowRequestWithoutToken(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.contains("/api/auth/signup") || requestURI.contains("/api/auth/signin");
    }
}