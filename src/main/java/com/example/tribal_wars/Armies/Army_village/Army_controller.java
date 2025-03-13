package com.example.tribal_wars.Armies.Army_village;

import com.example.tribal_wars.Exceptions.Exc_invalid_request;
import com.example.tribal_wars.Village.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/armies")
public class Army_controller {
    private final Army_service army_service;
    @Autowired
    public Army_controller(Army_service army_service) {
        this.army_service = army_service;
    }

    @PostMapping
    public ResponseEntity<Army> create_army(@RequestBody Army army){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.army_service.save_army(army));
    }
    @GetMapping("/{village_x}/{village_y}/{player_id}")
    public ResponseEntity<Army> get_army_by_id
            (@PathVariable Integer village_x, @PathVariable Integer village_y, @PathVariable Long player_id){
        return ResponseEntity.ok(
                this.army_service.get_army_by_id(new Army.Army_id(new Coordinates(village_x,village_y),player_id)));

    }
    @GetMapping("/all")
    public ResponseEntity<Set<Army>> get_all_armies(){
        return Optional.ofNullable(this.army_service.get_all_armies())
                .filter(armies -> !armies.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{village_x}/{village_y}/{player_id}")
    public ResponseEntity<Army> update_army
            (@PathVariable Integer village_x, @PathVariable Integer village_y, @PathVariable Long player_id, @RequestBody Army army){
        Army.Army_id id = new Army.Army_id(new Coordinates(village_x,village_y),player_id);
        return Optional.ofNullable(army)
                .filter(a -> id.equals(a.getId()))
                .map(a -> ResponseEntity.ok(this.army_service.put_army(id,a)))
                .orElseThrow(() -> new Exc_invalid_request("Parameter ID does not match it's body."));
    }



    @DeleteMapping("/{village_x}/{village_y}/{player_id}")
    public ResponseEntity<Void> delete_army
            (@PathVariable Integer village_x, @PathVariable Integer village_y, @PathVariable Long player_id){
        this.army_service.delete_army(new Army.Army_id(new Coordinates(village_x,village_y),player_id));
        return ResponseEntity.noContent().build();
    }
}
