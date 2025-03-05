package com.example.tribal_wars.Village;

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
        village.setPlayer_owner(null); // TODO: CREATE CONCRETE EXCEPTION HANDLERS
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Village created successfully.")
                .body(this.village_service.save_village(village));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Village> get_village_by_id(@PathVariable Long id){
        return this.village_service.get_village_by_id(id)
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
    @PutMapping("/{id}")
    public ResponseEntity<Village> update_village(@PathVariable Long id, @RequestBody Village village){
        if(!id.equals(village.getId()))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("Param ID does not match Body")
                    .build();
        else{
            Optional<Village> retrieved_village = this.village_service.get_village_by_id(id);
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
                            .header("Village with ID " + id + " not found.")
                            .build());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete_village(@PathVariable Long id){
        this.village_service.delete_village(id);
        return ResponseEntity.noContent().build();
    }
}
