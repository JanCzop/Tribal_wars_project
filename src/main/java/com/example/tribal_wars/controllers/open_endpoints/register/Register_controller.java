package com.example.tribal_wars.controllers.open_endpoints.register;

import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.services.login.Register_service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class Register_controller {
    private final Register_service register_service;
    @Autowired
    public Register_controller(Register_service registerService) {
        register_service = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Player> register(@Valid @RequestBody Register_DTO register_data){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(register_service.register(register_data));
    }
}
