package com.example.tribal_wars.Armies.Army_village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/armies")
public class Army_controller {
    private final Army_service army_service;
    @Autowired
    public Army_controller(Army_service army_service) {
        this.army_service = army_service;
    }
}
