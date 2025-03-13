package com.example.tribal_wars.Village;

import com.example.tribal_wars.Armies.Army_village.Army;
import com.example.tribal_wars.Armies.Army_village.Army_repository;
import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Exceptions.Exc_building_cannot_be_constructed;
import com.example.tribal_wars.Exceptions.Exc_item_not_found;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Player.Player_repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Village_service {
    private final Village_repository village_repository;
    private final Player_repository player_repository;
    private final Army_repository army_repository;
    private final Resources_service resources_service;
    private final Construction_service construction_service;

    @Autowired
    public Village_service(Village_repository village_repository,
                           Player_repository player_repository,
                           Army_repository army_repository,
                           Resources_service resources_service,
                           Construction_service construction_service) {
        this.village_repository = village_repository;
        this.player_repository = player_repository;
        this.army_repository = army_repository;
        this.resources_service = resources_service;
        this.construction_service = construction_service;

        Coordinates test_id = new Coordinates(1,1);
        Village test_village = new Village(test_id);
        this.construction_service.start_construction(test_village, Building_type.Blacksmith);
        save_village(test_village);
    }
    // TODO: CREATE EXCEPTIONS HANDLING WITH NON-EXISTING FOREIGN KEYS FOR ALL ENTITIES

    public Village save_village(Village village){
        return village_repository.save(village);
    }
    public void delete_village(Coordinates coordinates){
        village_repository.deleteById(coordinates);
    }
    public Village get_village_by_id(Coordinates id){
        return village_repository.findById(id)
                .orElseThrow(() -> new Exc_item_not_found("Village with Coordinates " + id + " not found."));
    }
    public Set<Village> get_all_villages(){
        return new HashSet<>(village_repository.findAll());
    }
    public Village put_village(Coordinates coordinates, Village village){
        Player player = this.player_repository.findById(village.getPlayer().getId())
                .orElseThrow(() -> new Exc_item_not_found("Player with ID " + coordinates + " not found."));
        Set<Army> armies = village.getArmies().stream()
                .map(army -> this.army_repository.findById(army.getId())
                        .orElseThrow(() -> new Exc_item_not_found("Army with ID " + coordinates + " not found.")))
                .collect(Collectors.toSet());
        return this.village_repository.findById(coordinates)
                .map(v -> {
                    v.setPlayer(player);
                    v.setArmies(armies);
                    v.setConstruction(village.getConstruction());
                    v.setRecruitment(village.getRecruitment());
                    v.setBuildings(village.getBuildings());
                    v.setResources(village.getResources());
                    v.setSpecialty(village.getSpecialty());
                    return this.village_repository.save(v);
                }).orElseThrow(() -> new Exc_item_not_found("Village with Coordinates " + coordinates + " not found."));
    }
    @Transactional
    public Village update_village_state(Coordinates coordinates){
        Village village = get_village_by_id(coordinates);

        resources_service.update_village_current_resource_state(village);
        construction_service.update_construction(village);

        return village_repository.save(village);
    }
    @Transactional
    public Village construct_building(Coordinates coordinates, Building_type type){
        return this.village_repository.findById(coordinates)
                .filter(v -> this.construction_service.is_construction_viable(type,v))
                .map(v -> {
                    this.construction_service.start_construction(v,type);
                    return v;
                }).orElseThrow(() -> new Exc_building_cannot_be_constructed("Construction is not viable"));
    }
}
