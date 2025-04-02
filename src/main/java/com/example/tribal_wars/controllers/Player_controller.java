package com.example.tribal_wars.controllers;


import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.exceptions.Exc_invalid_request;
import com.example.tribal_wars.services.player.Player_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class Player_controller {
    private final Player_service player_service;
    @Autowired
    public Player_controller(Player_service player_service) {
        this.player_service = player_service;
    }
    @PostMapping("/admin/create")
    public ResponseEntity<Player> create_player(@RequestBody Player player){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.player_service.save_player(player));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> get_player_by_id(@PathVariable Long id){
        return ResponseEntity.ok(this.player_service.get_player_by_id(id));
    }
    @GetMapping("/admin/all")
    public ResponseEntity<List<Player>> get_all_players(){
        return Optional.ofNullable(this.player_service.get_all_players())
                .filter(players -> !players.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<Player> put_player(@PathVariable Long id, @RequestBody Player player){
        return Optional.ofNullable(player)
                .filter(p -> id.equals(p.getId()))
                .map(p -> ResponseEntity.ok(this.player_service.put_player(id,p)))
                .orElseThrow(() -> new Exc_invalid_request("Parameter ID does not match it's body."));
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> delete_player(@PathVariable Long id){
        this.player_service.delete_player(id);
        return ResponseEntity.noContent().build();
    }


}
