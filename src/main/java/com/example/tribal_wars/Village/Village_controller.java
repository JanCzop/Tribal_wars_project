package com.example.tribal_wars.Village;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
                .header("Village created successfully.")
                .body(this.village_service.save_village(village));
    }
    @GetMapping("/{x}/{y}/update")
    public ResponseEntity<Village> update_village_state(@PathVariable Integer x, @PathVariable Integer y){
        System.out.println("im here");
        village_service.update_village_state(village_service.get_village_by_id(new Coordinates(x,y)));
        return null;
    }
    @GetMapping("/{x}/{y}")
    public ResponseEntity<Village> get_village_by_id(@PathVariable Integer x, @PathVariable Integer y){
        return this.village_service.get_village_by_id(new Coordinates(x,y))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    @GetMapping("/all")
    public ResponseEntity<List<Village>> get_all_villages(){
        List<Village> villages = this.village_service.get_all_villages();
        String header = "Total-count";
        if(villages.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .header(header,"0")
                    .build();
        else return ResponseEntity
                .status(HttpStatus.OK)
                .header(header,String.valueOf(villages.size()))
                .body(villages);
    }
    @PutMapping("/{x}/{y}")
    public ResponseEntity<Village> update_village(@PathVariable Integer x, @PathVariable Integer y, @RequestBody Village village){
        Coordinates coordinates = new Coordinates(x,y);
        if(!coordinates.equals(village.getCoordinates()))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("Param ID does not match Body")
                    .build();
        else{
            Optional<Village> retrieved_village = this.village_service.get_village_by_id(coordinates);
            return retrieved_village
                    .map(existing_village -> {
                        //existing_village.setPlayer_id(village.getPlayer_id());
                        existing_village.setBuildings(village.getBuildings());
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(this.village_service.save_village(existing_village));
                    })
                    .orElseGet(() -> ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .header("Village with ID " + x + "/" + y + " not found.")
                            .build());
        }
    }
    @DeleteMapping("/{x}/{y}")
    public ResponseEntity<Void> delete_village(@PathVariable Integer x, @PathVariable Integer y){
        this.village_service.delete_village(new Coordinates(x,y));
        return ResponseEntity.noContent().build();
    }
}
