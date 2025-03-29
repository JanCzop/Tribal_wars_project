package com.example.tribal_wars.security.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Basic_authentication_manager implements AuthenticationManager {
    private final Basic_authentication_provider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return this.provider.authenticate(authentication);
    }
}
