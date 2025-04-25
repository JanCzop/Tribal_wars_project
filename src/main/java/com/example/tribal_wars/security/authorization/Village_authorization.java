package com.example.tribal_wars.security.authorization;

import com.example.tribal_wars.entities.village.embbed.Coordinates;
import com.example.tribal_wars.repositories.Village_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("village_authorization")
@RequiredArgsConstructor
public class Village_authorization {
    private final Village_repository village_repository;

    public boolean is_user_owner(int x, int y){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) return false;
        String username = auth.getName();
        return village_repository.existsByCoordinatesAndPlayer_Username(new Coordinates(x,y), username);
    }
}
