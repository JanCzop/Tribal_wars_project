package com.example.tribal_wars.services.player;

import com.example.tribal_wars.entities.player.Authority;
import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.exceptions.Exc_item_not_found;
import com.example.tribal_wars.repositories.Player_repository;
import com.example.tribal_wars.repositories.Village_repository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class Player_service {
    private final Player_repository player_repository;
    private final Village_repository village_repository;
    @Autowired
    public Player_service(Player_repository player_repository, Village_repository villageRepository) {
        this.player_repository = player_repository;
        village_repository = villageRepository;
    }

    @PostConstruct
    public void player_tester(){
        Player admin = new Player();
        admin.setUsername("Admin");
        admin.setEmail("admin.tribal@email.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("password"));
        admin.setAuthorities(new HashSet<>());
        Authority admin_auth = new Authority();
        admin_auth.setName("ROLE_ADMIN");
        admin.getAuthorities().add(admin_auth);
        this.player_repository.save(admin);
    }



    public void validate_foreign_keys(Player player) {
        Optional.ofNullable(player.getVillages())
                .ifPresent(villages -> villages.forEach(village ->
                        Optional.ofNullable(village.getCoordinates())
                                .ifPresent(coordinates -> this.village_repository.findById(coordinates)
                                        .orElseThrow(() -> new Exc_item_not_found("Village with Coordinates " + coordinates + " not found."))
                                )
                ));
    }


    public Player save_player(Player player){
        validate_foreign_keys(player);
        return this.player_repository.save(player);
    }
    public void delete_player(Long id){
        this.player_repository.deleteById(id);
    }
    public Player get_player_by_id(Long id){
        return this.player_repository.findById(id)
                .orElseThrow(() -> new Exc_item_not_found("Player with ID " + id + " not found."));
    }
    public List<Player> get_all_players(){
        return this.player_repository.findAll();
    }
    public Player put_player(Long id, Player player) {
        validate_foreign_keys(player);
        return player_repository.findById(id).map(p -> {
            p.setUsername(player.getUsername());
            p.setPassword(player.getPassword());
            p.setEmail(player.getEmail());
            p.setVillages(player.getVillages());
            return player_repository.save(p);
        }) .orElseThrow(() -> new Exc_item_not_found("Player with ID " + id + " not found."));
    }
}
