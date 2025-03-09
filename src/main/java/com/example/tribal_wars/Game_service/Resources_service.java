package com.example.tribal_wars.Game_service;

import com.example.tribal_wars.Config.Buildings_config;
import com.example.tribal_wars.Config.Game_config;
import com.example.tribal_wars.Village.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class Resources_service {
    private final Game_config game_config;
    private final Buildings_config buildings_config;

    // TODO: CALCULATING RESOURCES FROM ACTUAL BUILDINGS LEVEL
    @Autowired
    public Resources_service(Game_config game_config, Buildings_config buildings_config) {
        this.game_config = game_config;
        this.buildings_config = buildings_config;
    }
    public void update_village_current_resource_state(Village village) {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime last_update = village.getResources().getResource_last_update();

        if (last_update.isAfter(now)) return;

        int interval = this.game_config.getResources_harvest_interval();
        long elapsed_seconds = Duration.between(last_update, now).getSeconds();
        int elapsed_intervals = (int) (elapsed_seconds / interval);

        if (elapsed_intervals > 0) {
            System.out.println("Intervals: " + elapsed_intervals);
            System.out.println("Resources to harvest: " + this.game_config.getResources_harvest_interval());
            System.out.println("Current village id: " + village.getCoordinates());
            System.out.println("Village resources before update: " + village.getResources());

            LocalDateTime new_update_time = last_update.plusSeconds((long) elapsed_intervals * interval);
            village.getResources().update_all_resources(
                    buildings_config.getResource_production_per_level() * elapsed_intervals);
            village.getResources().setResource_last_update(new_update_time);

            System.out.println("Village resources after update: " + village.getResources());
        }
    }

}
