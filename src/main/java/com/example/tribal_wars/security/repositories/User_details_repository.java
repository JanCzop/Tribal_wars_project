package com.example.tribal_wars.security.repositories;

import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.repositories.Player_repository;
import com.example.tribal_wars.security.models.Custom_user;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class User_details_repository implements UserDetailsService {
    private final Player_repository player_repository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> user = this.player_repository.findByUsernameEAGER(username);
        return user.map(Custom_user::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
