package com.example.tribal_wars.Armies.Army_village;


import com.example.tribal_wars.Exceptions.Exc_item_not_found;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Player.Player_repository;
import com.example.tribal_wars.Village.Village;
import com.example.tribal_wars.Village.Village_repository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class Army_service {

    private final Army_repository army_repository;
    private final Village_repository village_repository;
    private final Player_repository player_repository;
    @Autowired
    public Army_service(Army_repository army_repository, Village_repository villageRepository, Player_repository playerRepository) {
        this.army_repository = army_repository;
        village_repository = villageRepository;
        player_repository = playerRepository;
    }


    public void validate_foreign_keys(Army army) {
        Optional.ofNullable(army.getVillage())
                .ifPresent(village -> this.village_repository.findById(village.getCoordinates())
                        .orElseThrow(() -> new Exc_item_not_found("Village with Coordinates " + village.getCoordinates() + " not found.")));

        Optional.ofNullable(army.getPlayer())
                .ifPresent(player -> this.player_repository.findById(player.getId())
                        .orElseThrow(() -> new Exc_item_not_found("Player with ID " + player.getId() + " not found.")));
    }

    public Army save_army(Army army){
        validate_foreign_keys(army);
        return this.army_repository.save(army);
    }
    public void delete_army(Long id){
        this.army_repository.deleteById(id);
    }
    public Army get_army_by_id(Long id){
        return this.army_repository.findById(id)
                .orElseThrow(() -> new Exc_item_not_found("Army with ID " + id + " not found."));
    }
    public Set<Army> get_all_armies(){
        return new HashSet<>(this.army_repository.findAll());
    }
    public Army put_army(Long id, Army army){
        validate_foreign_keys(army);
        return this.army_repository.findById(id).map(a -> {
            a.setVillage(army.getVillage());
            a.setPlayer(army.getPlayer());
            a.setArmy_details(army.getArmy_details());
            return this.army_repository.save(a);
        }) .orElseThrow(() -> new Exc_item_not_found("Army with ID " + id + " not found."));
    }
}
