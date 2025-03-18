package com.example.tribal_wars.backend.Armies.Army_village;

import com.example.tribal_wars.backend.Exceptions.Exc_invalid_request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<Army> get_army_by_id(@PathVariable Long id){
        return ResponseEntity.ok(
                this.army_service.get_army_by_id(id));

    }
    @GetMapping("/all")
    public ResponseEntity<Set<Army>> get_all_armies(){
        return Optional.ofNullable(this.army_service.get_all_armies())
                .filter(armies -> !armies.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Army> update_army(@PathVariable Long id, @RequestBody Army army){
        return Optional.ofNullable(army)
                .filter(a -> id.equals(a.getId()))
                .map(a -> ResponseEntity.ok(this.army_service.put_army(id,a)))
                .orElseThrow(() -> new Exc_invalid_request("Parameter ID does not match it's body."));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete_army
            (@PathVariable Long id){
        this.army_service.delete_army(id);
        return ResponseEntity.noContent().build();
    }
}
