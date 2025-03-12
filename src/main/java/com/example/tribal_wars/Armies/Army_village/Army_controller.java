package com.example.tribal_wars.Armies.Army_village;

import com.example.tribal_wars.Village.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
                .header("Army created successfully.")
                .body(this.army_service.save_army(army));
    }
    @GetMapping("/{village_x}/{village_y}/{player_id}")
    public ResponseEntity<Army> get_army_by_id
            (@PathVariable Integer village_x, @PathVariable Integer village_y, @PathVariable Long player_id){
        return this.army_service.get_army_by_id(new Army.Army_id(new Coordinates(village_x,village_y),player_id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Army>> get_all_armys(){
        List<Army> armies = this.army_service.get_all_armies();
        String header = "Total-count";
        if(armies.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .header(header,"0")
                    .build();
        else return ResponseEntity
                .status(HttpStatus.OK)
                .header(header,String.valueOf(armies.size()))
                .body(armies);
    }

    @PutMapping("/{village_x}/{village_y}/{player_id}")
    public ResponseEntity<Army> update_army
            (@PathVariable Integer village_x, @PathVariable Integer village_y, @PathVariable Long player_id, @RequestBody Army army){
        Army.Army_id id = new Army.Army_id(new Coordinates(village_x,village_y),player_id);
        return id.equals(army.getId())
                ? army_service.put_army(id,army)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .header("Player with ID " + id + " not found.")
                        .build())
                : ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("Param ID does not match Body")
                .build();
    }



    @DeleteMapping("/{village_x}/{village_y}/{player_id}")
    public ResponseEntity<Void> delete_army
            (@PathVariable Integer village_x, @PathVariable Integer village_y, @PathVariable Long player_id){
        this.army_service.delete_army(new Army.Army_id(new Coordinates(village_x,village_y),player_id));
        return ResponseEntity.noContent().build();
    }
}
