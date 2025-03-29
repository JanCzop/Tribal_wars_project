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
@Component
@AllArgsConstructor
public class Custom_basic_authentication_filter extends OncePerRequestFilter {
    private final Basic_authentication_manager manager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");

        if(username != null && password != null){
            Basic_authentication auth = new Basic_authentication(username,password);
            Authentication provided_auth = this.manager.authenticate(auth);
            if(provided_auth != null && provided_auth.isAuthenticated())
                SecurityContextHolder.getContext().setAuthentication(provided_auth);
            else response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request,response);
    }
}
