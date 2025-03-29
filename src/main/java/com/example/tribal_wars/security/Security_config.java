package com.example.tribal_wars.security;

import com.example.tribal_wars.security.filters.Custom_basic_authentication_filter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class Security_config {
    private final Custom_basic_authentication_filter custom_basic_authentication_filter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.
                authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .addFilterBefore(custom_basic_authentication_filter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
