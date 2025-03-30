package com.example.tribal_wars.security.filters;

import com.example.tribal_wars.security.authentication.Basic_authentication;
import com.example.tribal_wars.security.authentication.Basic_authentication_manager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@AllArgsConstructor
public class Custom_basic_authentication_filter extends OncePerRequestFilter {
    private final Basic_authentication_manager manager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String base64Credentials = authHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

            String[] values = credentials.split(":", 2);
            if (values.length == 2) {
                String username = values[0];
                String password = values[1];

                Basic_authentication auth = new Basic_authentication(username, password);
                Authentication providedAuth = this.manager.authenticate(auth);

                if (providedAuth != null && providedAuth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(providedAuth);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
