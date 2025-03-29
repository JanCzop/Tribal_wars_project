package com.example.tribal_wars.security.models;

import com.example.tribal_wars.entities.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Custom_user implements UserDetails {
    private final Player player;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.player.getAuthorities().stream()
                .map(Custom_authority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.player.getPassword();
    }

    @Override
    public String getUsername() {
        return this.player.getUsername();
    }
}
