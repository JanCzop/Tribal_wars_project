package com.example.tribal_wars.Armies.Army_village;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Army_service {

    private final Army_repository army_repository;
    @Autowired
    public Army_service(Army_repository army_repository) {
        this.army_repository = army_repository;
    }

    public Army save_army(Army army){
        return this.army_repository.save(army);
    }
    public void delete_army(Army.Army_id id){
        this.army_repository.deleteById(id);
    }
    public Optional<Army> get_army_by_id(Army.Army_id id){
        return this.army_repository.findById(id);
    }
    public List<Army> get_all_armies(){
        return this.army_repository.findAll();
    }
    public Optional<Army> put_army(Army.Army_id id, Army army){
        return this.army_repository.findById(id).map(a -> {
            a.setVillage(army.getVillage());
            a.setPlayer(army.getPlayer());
            a.setArmy_details(army.getArmy_details());
            return this.army_repository.save(a);
        });
    }
}
