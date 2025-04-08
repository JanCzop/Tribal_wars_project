package com.example.tribal_wars.security;

import com.example.tribal_wars.security.authentication.Basic_authentication_manager;
import com.example.tribal_wars.security.authentication.Basic_authentication_provider;
import com.example.tribal_wars.security.filters.Custom_basic_authentication_filter;
import com.example.tribal_wars.security.repositories.User_details_repository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@AllArgsConstructor
public class Security_config {
    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            Custom_basic_authentication_filter custom_basic_authentication_filter) throws Exception{
        http.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers(regexMatcher("^/api/.*/admin/.*$")).hasRole("ADMIN")
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/**").hasAnyRole("ADMIN","PLAYER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(custom_basic_authentication_filter, BasicAuthenticationFilter.class);

        return http.build();
    }
    @Bean Custom_basic_authentication_filter custom_basic_authentication_filter(Basic_authentication_manager manager){
        return new Custom_basic_authentication_filter(manager);
    }
    @Bean
    Basic_authentication_manager basic_authentication_manager(Basic_authentication_provider provider){
        return new Basic_authentication_manager(provider);
    }
    @Bean
    Basic_authentication_provider basic_authentication_provider(User_details_repository repository, BCryptPasswordEncoder encoder){
        return new Basic_authentication_provider(repository,encoder);
    }
    @Bean
    BCryptPasswordEncoder password_encoder(){
        return new BCryptPasswordEncoder();
    }
}
