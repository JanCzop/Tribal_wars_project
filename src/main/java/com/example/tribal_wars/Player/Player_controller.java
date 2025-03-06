package com.example.tribal_wars.Player;


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
    @PostMapping
    public ResponseEntity<Player> create_player(@RequestBody Player player){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Player created successfully.")
                .body(this.player_service.save_player(player));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> get_player_by_id(@PathVariable Long id){
        return this.player_service.get_player_by_id(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Player>> get_all_players(){
        List<Player> players = this.player_service.get_all_players();
        String header = "Total-count";
        if(players.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .header(header,"0")
                    .build();
        else return ResponseEntity
                .status(HttpStatus.OK)
                .header(header,String.valueOf(players.size()))
                .body(players);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Player> update_player(@PathVariable Long id, @RequestBody Player player){
        if(!id.equals(player.getId()))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("Param ID does not match Body")
                    .build();
        else{
            Optional<Player> retrieved_player = this.player_service.get_player_by_id(id);
            return retrieved_player
                    .map(existing_player -> {
                        existing_player.setUsername(player.getUsername());
                        existing_player.setPassword(player.getPassword());
                        existing_player.setEmail(player.getEmail());
                        existing_player.setVillages(player.getVillages());
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(this.player_service.save_player(existing_player));
                    })
                    .orElseGet(() -> ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .header("Player with ID " + id + " not found.")
                            .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete_player(@PathVariable Long id){
        this.player_service.delete_player(id);
        return ResponseEntity.noContent().build();
    }


}
