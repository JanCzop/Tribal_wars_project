package com.example.tribal_wars.Armies.Army_commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .header("Command created successfully.")
                .body(this.command_service.save_command(command));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Command> get_command_by_id(@PathVariable Long id){
        return this.command_service.get_command_by_id(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Command>> get_all_commands(){
        List<Command> armies = this.command_service.get_all_commands();
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

    @PutMapping("/{id}")
    public ResponseEntity<Command> update_command(@PathVariable Long id, @RequestBody Command command){
        return id.equals(command.getId())
                ? command_service.put_command(id,command)
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



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete_command(@PathVariable Long id){
        this.command_service.delete_command(id);
        return ResponseEntity.noContent().build();
    }
}
