package com.example.tribal_wars.services.login;

import com.example.tribal_wars.controllers.open_endpoints.register.Register_DTO;
import com.example.tribal_wars.entities.army.Army;
import com.example.tribal_wars.entities.player.Authority;
import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.repositories.Army_repository;
import com.example.tribal_wars.repositories.Player_repository;
import com.example.tribal_wars.repositories.Village_repository;
import com.example.tribal_wars.security.Security_config;
import com.example.tribal_wars.services.village.Coordinates_generator_service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class Register_service {
    private static final String DEFAULT_PLAYER_AUTHORITY = "PLAYER";

    private final Player_repository player_repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Coordinates_generator_service generator;

    @Transactional
    public Player register(Register_DTO register_data){
        if (player_repository.existsByUsername(register_data.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already taken");
        if (player_repository.existsByEmail(register_data.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        // Player
        Player player = new Player();
        player.setUsername(register_data.getUsername());
        player.setEmail(register_data.getEmail());
        player.setPassword(passwordEncoder.encode(register_data.getPassword()));
        // Authority
        player.setAuthorities(new HashSet<>());
        Authority authority = new Authority();
        authority.setName(DEFAULT_PLAYER_AUTHORITY);
        player.getAuthorities().add(authority);
        // Village
        player.setVillages(new HashSet<>());
        Village village = new Village();
        village.setCoordinates(generator.generate_coordinates());
        village.setPlayer(player);
        village.setName(player.getUsername() + " village");
        player.getVillages().add(village);
        // Army
        Army army = new Army();
        village.setLocal_army(army);
        army.setVillage(village);

        return this.player_repository.save(player);
    }
}
