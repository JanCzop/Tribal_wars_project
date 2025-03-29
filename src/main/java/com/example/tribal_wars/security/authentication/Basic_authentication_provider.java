package com.example.tribal_wars.security.authentication;

import com.example.tribal_wars.repositories.Player_repository;
import com.example.tribal_wars.security.models.Custom_user;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Basic_authentication_provider implements AuthenticationProvider {
    private static final Class<?> supported_class = Basic_authentication.class;
    private final Player_repository player_repository;
    private final BCryptPasswordEncoder password_encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Basic_authentication basic_auth = (Basic_authentication) authentication;

        Custom_user user = this.player_repository.findByUsername(basic_auth.getName())
                .map(Custom_user::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found.")); // TODO: EXCEPTION HANDLE
        if(!password_encoder.matches
                (basic_auth.getCredentials().toString(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials."); // TODO: EXCEPTION HANDLE
        else {
            basic_auth.setAuthenticated(true);
            return basic_auth;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return supported_class.equals(authentication);
    }
}
