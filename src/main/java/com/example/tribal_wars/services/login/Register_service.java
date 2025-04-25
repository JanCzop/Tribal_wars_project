package com.example.tribal_wars.services.login;

import com.example.tribal_wars.config.Buildings_config;
import com.example.tribal_wars.config.Village_config;
import com.example.tribal_wars.controllers.open_endpoints.register.Register_DTO;
import com.example.tribal_wars.entities.army.Army;
import com.example.tribal_wars.entities.player.Authority;
import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.entities.village.embbed.Village_resources;
import com.example.tribal_wars.enums.Building_type;
import com.example.tribal_wars.enums.Specialty;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class Register_service {
    private static final String DEFAULT_PLAYER_AUTHORITY = "ROLE_PLAYER";

    private final Player_repository player_repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Coordinates_generator_service generator;

    private final Village_config village_config;
    private final Buildings_config buildings_config;

    @Transactional
    public Player register(Register_DTO register_data){
        if (player_repository.existsByUsername(register_data.username()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already taken");
        if (player_repository.existsByEmail(register_data.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        // Player
        Player player = new Player();
        player.setUsername(register_data.username());
        player.setEmail(register_data.email());
        player.setPassword(passwordEncoder.encode(register_data.password()));
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
        initialize_village(village);
        // Army
        Army army = new Army();
        village.setLocal_army(army);
        army.setVillage(village);

        return this.player_repository.save(player);
    }


    public void initialize_village(Village village){
        village.setBuildings(new HashMap<>());
        for (Building_type type : Building_type.values()) {
            if (type.isNecessary_building()) village.getBuildings().put(type, Building_type.MINIMUM_NECESSARY_BUILDING_LEVEL);
            else village.getBuildings().put(type, Building_type.MINIMUM_UNNECESSARY_BUILDING_LEVEL);
        }

        village.setConstruction(null);
        village.setRecruitment(null);

        Village_resources resources = new Village_resources();
        int resource_cap = village_config.getBase_resource_capacity() + buildings_config.getGranary_capacity_per_level();
        int gold_cap = village_config.getBase_gold_capacity();

        resources.setResources_capacity(resource_cap);
        resources.setGold_capacity(gold_cap);
        resources.setCurrent_wood(resource_cap);
        resources.setCurrent_stone(resource_cap);
        resources.setCurrent_iron(resource_cap);
        resources.setCurrent_gold(gold_cap);

        resources.setCache_capacity(0);

        village.setResources(resources);
        village.setSpecialty(Specialty.None);
    }

}
