package com.example.tribal_wars.Village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Village_service {
    private final Village_repository village_repository;
    @Autowired
    public Village_service(Village_repository village_repository) {
        this.village_repository = village_repository;
    }

    public Village save_village(Village village){
        return village_repository.save(village);
    }
    public void delete_village(Long id){
        village_repository.deleteById(id);
    }
    public Optional<Village> get_village_by_id(Long id){
        return village_repository.findById(id);
    }
    public List<Village> get_all_villages(){
        return village_repository.findAll();
    }
}
