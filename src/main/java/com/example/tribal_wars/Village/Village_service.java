package com.example.tribal_wars.Village;

import com.example.tribal_wars.Armies.Army_village.Army;
import com.example.tribal_wars.Armies.Army_village.Army_repository;
import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Exceptions.Exc_building_cannot_be_constructed;
import com.example.tribal_wars.Exceptions.Exc_item_not_found;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Player.Player_repository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private final Recruitment_service recruitment_service;

    @Autowired
    public Village_service(Village_repository village_repository,
                           Player_repository player_repository,
                           Army_repository army_repository,
                           Resources_service resources_service,
                           Construction_service construction_service,
                           Recruitment_service recruitment_service) {
        this.village_repository = village_repository;
        this.player_repository = player_repository;
        this.army_repository = army_repository;
        this.resources_service = resources_service;
        this.construction_service = construction_service;
        this.recruitment_service = recruitment_service;

    }
    @PostConstruct
    public void init_tester(){
        Coordinates test_id = new Coordinates(1,1);
        Village test_village = new Village(test_id);
        this.recruitment_service.do_nothing(2L);
        System.out.println(this.construction_service.is_construction_viable(Building_type.Blacksmith,test_village));
        save_village(test_village);
        this.construction_service.start_construction(test_village, Building_type.Blacksmith);
        save_village(test_village);
    }


    public void validate_foreign_keys(Village village) {
        Optional.ofNullable(village.getPlayer())
                .ifPresent(player -> this.player_repository.findById(player.getId())
                        .orElseThrow(() -> new Exc_item_not_found("Player with ID " + player.getId() + " not found.")));
        village.getArmies().forEach(army ->
                Optional.ofNullable(army.getId())
                        .ifPresent(id -> this.army_repository.findById(id)
                                .orElseThrow(() -> new Exc_item_not_found("Army with ID " + id + " not found."))));
        Optional.ofNullable(village.getConstruction())
                .ifPresent(construction -> this.construction_service.getConstruction_repository()
                        .findById(construction.getId())
                        .orElseThrow(() -> new Exc_item_not_found("Construction with ID " + construction.getId() + " not found.")));
        Optional.ofNullable(village.getRecruitment())
                .ifPresent(recruitment -> this.recruitment_service.getRecruitment_repository()
                        .findById(recruitment.getId())
                        .orElseThrow(() -> new Exc_item_not_found("Recruitment with ID " + recruitment.getId() + " not found.")));
        Optional.ofNullable(village.getResources())
                .orElseThrow(() -> new Exc_item_not_found("Resources are null for village with coordinates " + village.getCoordinates()));
    }



    public Village save_village(Village village){
        //validate_foreign_keys(village);
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
        //validate_foreign_keys(village);
        return this.village_repository.findById(coordinates)
                .map(v -> {
                    v.setPlayer(village.getPlayer());
                    v.setArmies(village.getArmies());
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
        construction_service.check_construction(village);

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
