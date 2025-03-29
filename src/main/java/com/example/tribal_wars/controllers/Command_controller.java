package com.example.tribal_wars.controllers;


import com.example.tribal_wars.services.army.Command_service;
import com.example.tribal_wars.entities.army.Command;
import com.example.tribal_wars.exceptions.Exc_invalid_request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commands")
public class Command_controller {
    private final Command_service command_service;
    @Autowired
    public Command_controller(Command_service command_service) {
        this.command_service = command_service;
    }

    @PostMapping
    public ResponseEntity<Command> create_command(@RequestBody Command command){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.command_service.save_command(command));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Command> get_command_by_id(@PathVariable Long id){
        return ResponseEntity.ok(this.command_service.get_command_by_id(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Command>> get_all_commands(){
        return Optional.ofNullable(this.command_service.get_all_commands())
                .filter(players -> !players.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Command> put_command(@PathVariable Long id, @RequestBody Command command){
        return Optional.ofNullable(command)
                .filter(c -> id.equals(c.getId()))
                .map(c -> ResponseEntity.ok(this.command_service.put_command(id,c)))
                .orElseThrow(() -> new Exc_invalid_request("Parameter ID does not match it's body."));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete_command(@PathVariable Long id){
        this.command_service.delete_command(id);
        return ResponseEntity.noContent().build();
    }
}
