package com.example.tribal_wars.controllers.private_endpoints;

import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.entities.village.embbed.Coordinates;
import com.example.tribal_wars.repositories.Village_repository;
import com.example.tribal_wars.services.village.Village_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/village")
@RequiredArgsConstructor
public class Gameplay_village_controller {

    private final Village_service village_service;

    @PutMapping("/{x}/{y}/change_name")
    @PreAuthorize("@village_authorization.is_user_owner(#x, #y)")
    public ResponseEntity<Village> change_name(@PathVariable Integer x,
                                               @PathVariable Integer y,
                                               @RequestBody Change_village_name_DTO name) {
        Village updated = village_service.change_village_name(x, y, name.name());
        return ResponseEntity.ok(updated);
    }

}
