package com.example.tribal_wars.controllers.private_endpoints;

import com.example.tribal_wars.controllers.private_endpoints.DTOs.Change_village_name_DTO;
import com.example.tribal_wars.controllers.private_endpoints.DTOs.Construct_building_DTO;
import com.example.tribal_wars.controllers.private_endpoints.DTOs.Recruit_army_DTO;
import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.entities.village.embbed.Coordinates;
import com.example.tribal_wars.services.village.Village_service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/village")
@RequiredArgsConstructor
public class Gameplay_village_controller {

    private final Village_service village_service;

    @PutMapping("/{x}/{y}/namechange")
    @PreAuthorize("@village_authorization.is_user_owner(#x, #y)")
    public ResponseEntity<Village> change_name(@PathVariable Integer x,
                                               @PathVariable Integer y,
                                               @Valid @RequestBody Change_village_name_DTO name) {
        return ResponseEntity.ok(village_service.change_village_name(new Coordinates(x,y), name.name()));
    }

    @PostMapping("/{x}/{y}/construct")
    @PreAuthorize("@village_authorization.is_user_owner(#x, #y)")
    public ResponseEntity<Village> construct_building(@PathVariable Integer x,
                                                      @PathVariable Integer y,
                                                      @Valid @RequestBody Construct_building_DTO dto) {
        return ResponseEntity.ok(village_service.construct_building(new Coordinates(x,y), dto.building_type()));
    }

    @PostMapping("/{x}/{y}/recruit")
    @PreAuthorize("@village_authorization.is_user_owner(#x, #y)")
    public ResponseEntity<Village> recruit_army(@PathVariable Integer x,
                                                @PathVariable Integer y,
                                                @Valid @RequestBody Recruit_army_DTO dto) {
        return ResponseEntity.ok(village_service.recruit_army(new Coordinates(x, y), dto.army_details()));
    }



}
