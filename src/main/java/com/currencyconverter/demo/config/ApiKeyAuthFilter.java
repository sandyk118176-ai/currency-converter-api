package com.currencyconverter.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${app.api-key}")
    private String validApiKey;

    private static final String API_KEY_HEADER = "X-API-KEY";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                      FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Let H2 console requests pass through without an API key
        if (path.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        String providedKey = request.getHeader(API_KEY_HEADER);

        if (validApiKey.equals(providedKey)) {
            UsernamePasswordAuthenticationToken authToken = 
                   new UsernamePasswordAuthenticationToken("api-user", null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);       
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid or missing API key\"}");
        }
    }

    
}
