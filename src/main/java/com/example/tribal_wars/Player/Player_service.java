package com.example.tribal_wars.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Player_service {
    private final Player_repository player_repository;
    @Autowired
    public Player_service(Player_repository player_repository) {
        this.player_repository = player_repository;
    }

    public Player save_player(Player player){
        return this.player_repository.save(player);
    }
    public void delete_player(Long id){
        this.player_repository.deleteById(id);
    }
    public Optional<Player> get_player_by_id(Long id){
        return this.player_repository.findById(id);
    }
    public List<Player> get_all_players(){
        return this.player_repository.findAll();
    }
    public Optional<Player> put_player(Long id, Player player) {
        return player_repository.findById(id).map(p -> {
            p.setUsername(player.getUsername());
            p.setPassword(player.getPassword());
            p.setEmail(player.getEmail());
            p.setVillages(player.getVillages());
            return player_repository.save(p);
        });
    }
}
