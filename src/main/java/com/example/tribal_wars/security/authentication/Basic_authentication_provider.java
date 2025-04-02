package com.example.tribal_wars.security.authentication;

import com.example.tribal_wars.repositories.Player_repository;
import com.example.tribal_wars.security.models.Custom_authority;
import com.example.tribal_wars.security.models.Custom_user;
import com.example.tribal_wars.security.repositories.User_details_repository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class Basic_authentication_provider implements AuthenticationProvider {
    private static final Class<?> supported_class = Basic_authentication.class;
    private final User_details_repository user_repository;
    private final BCryptPasswordEncoder password_encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Basic_authentication basic_auth = (Basic_authentication) authentication;

        Custom_user user = (Custom_user) this.user_repository.loadUserByUsername(basic_auth.getName());
        if(!password_encoder.matches
                (basic_auth.getCredentials().toString(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials.");
        else {
            basic_auth.setAuthenticated(true);
            basic_auth.setAuthorities(user.getAuthorities());
            return basic_auth;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return supported_class.equals(authentication);
    }
}
