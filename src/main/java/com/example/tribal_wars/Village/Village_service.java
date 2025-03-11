package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
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
        return village_repository.findById(id);
    }
    public List<Village> get_all_villages(){
        return village_repository.findAll();
    }
    @Transactional
    public Optional<Village> update_village_state(Optional<Village> village){
        return village.map(v -> {
            this.resources_service.update_village_current_resource_state(v);
            this.construction_service.update_construction(v);
            return this.village_repository.save(v);
        });
    }
    @Transactional
    public Optional<Village> construct_building(Optional<Village> village, Building_type type){
        village.filter(v -> this.construction_service.is_construction_viable(type,v))
                .ifPresent(v -> this.construction_service.start_construction(v,type));
        return village;
    }
}
