package com.example.tribal_wars.Game_service;

import com.example.tribal_wars.Village.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Resources_service {
    private final Game_config game_config;
    @Autowired
    public Resources_service(Game_config game_config) {
        this.game_config = game_config;
    }
    public void update_village_current_resource_state(Object o){
        System.out.println(game_config.getResources_harvest_interval());
    }
}
