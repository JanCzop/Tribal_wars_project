package com.example.tribal_wars.Game_service;

import com.example.tribal_wars.Config.Game_config;
import com.example.tribal_wars.Village.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class Resources_service {
    private final Game_config game_config;
    @Autowired
    public Resources_service(Game_config game_config) {
        this.game_config = game_config;
    }
    public void update_village_current_resource_state(Village village){ // TODO: ALL CURRENT ACTIONS ARE SPECIFIED FOR GMT+1 TIMEZONE
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last_update = village.getResources().getResource_last_update();

        if(last_update.isAfter(now)) return;

        int elapsed_intervals = (int) Duration.between(last_update,now).getSeconds() / this.game_config.getResources_harvest_interval();
        if(elapsed_intervals > 0){

        }
    }

    private void update_resources(int intervals){

    }
}
