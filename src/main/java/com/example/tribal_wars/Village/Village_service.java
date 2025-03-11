package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Game_service.Construction_service;
import com.example.tribal_wars.Game_service.Resources_service;
import com.example.tribal_wars.Units.Army_details;
import com.example.tribal_wars.Units.Unit_type;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Village_service {
    private final Village_repository village_repository;
    private final Resources_service resources_service;
    private final Construction_service construction_service;
    @Autowired
    public Village_service(Village_repository village_repository,
                           Resources_service resources_service,
                           Construction_service construction_service) {
        this.village_repository = village_repository;
        this.resources_service = resources_service;
        this.construction_service = construction_service;

        Coordinates test_id = new Coordinates(1,1);
        Village test_village = new Village(test_id);
        this.construction_service.start_construction(test_village, Building_type.Blacksmith);
        save_village(test_village);
    }


    public Village save_village(Village village){
        return village_repository.save(village);
    }
    public void delete_village(Coordinates id){
        village_repository.deleteById(id);
    }
    public Optional<Village> get_village_by_id(Coordinates id){
        return update_village_state(village_repository.findById(id));
    }
    public List<Village> get_all_villages(){
        return village_repository.findAll();
    }
    @Transactional
    public Optional<Village> update_village_state(Optional<Village> village){
        if(village.isPresent()) {
            this.resources_service.update_village_current_resource_state(village.get());
            this.construction_service.update_construction(village.get());
            return Optional.of(this.village_repository.save(village.get()));
        }
        return Optional.empty();
    }
}
