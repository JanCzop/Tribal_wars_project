package com.example.tribal_wars.Village;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Exceptions.Exc_invalid_request;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/villages")
public class Village_controller {
    private final Village_service village_service;
    @Autowired
    public Village_controller(Village_service village_service) {
        this.village_service = village_service;
    }


    @PostMapping
    public ResponseEntity<Village> create_village(@RequestBody Village village){
        //village.setPlayer_owner(null); // TODO: CREATE CONCRETE EXCEPTION HANDLERS
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.village_service.save_village(village));
    }

    @GetMapping("/{x}/{y}/update")
    public ResponseEntity<Village> update_village_state(@PathVariable Integer x, @PathVariable Integer y){
        return ResponseEntity.ok
                (this.village_service.update_village_state(new Coordinates(x,y)));

    }

    @GetMapping("/{x}/{y}")
    public ResponseEntity<Village> get_village_by_id(@PathVariable Integer x, @PathVariable Integer y){
        return ResponseEntity.ok(this.village_service.get_village_by_id(new Coordinates(x,y)));


    }
    @GetMapping("/all")
    public ResponseEntity<Set<Village>> get_all_villages(){
        return Optional.ofNullable(this.village_service.get_all_villages())
                .filter(villages -> !villages.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @PutMapping("/{x}/{y}")
    public ResponseEntity<Village> update_village(@PathVariable Integer x, @PathVariable Integer y, @RequestBody Village village){
        Coordinates id = new Coordinates(x,y);
        return Optional.ofNullable(village)
                .filter(v -> id.equals(v.getCoordinates()))
                .map(v -> ResponseEntity.ok(this.village_service.put_village(id,v)))
                .orElseThrow(() -> new Exc_invalid_request("Parameter ID does not match it's body."));
    }
    @DeleteMapping("/{x}/{y}")
    public ResponseEntity<Void> delete_village(@PathVariable Integer x, @PathVariable Integer y){
        this.village_service.delete_village(new Coordinates(x,y));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{x}/{y}/build")
    public ResponseEntity<Village> construct_building(@PathVariable Integer x, @PathVariable Integer y, @RequestBody String building_type_name){
        return ResponseEntity.ok(this.village_service.construct_building(
                new Coordinates(x,y), Building_type.valueOf(building_type_name)));
    }

    @PostMapping("/{x}/{y}/recruit")
    public ResponseEntity<Village> recruit_army(@PathVariable Integer x, @PathVariable Integer y, @RequestBody Army_details army_details){
        return ResponseEntity.ok(this.village_service.recruit_army(new Coordinates(x,y), army_details));
    }


}
