package com.example.tribal_wars.security.models;

import com.example.tribal_wars.entities.player.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public class Custom_authority implements GrantedAuthority {
    private static final String PREFIX = "ROLE_";
    private final Authority authority;
    @Override
    public String getAuthority() {
        return this.authority.getName();
    }
}
