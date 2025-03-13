package com.example.tribal_wars.Village.Resources;

import com.example.tribal_wars.Config.Buildings_config;
import com.example.tribal_wars.Config.Game_config;
import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Enums.Resource;
import com.example.tribal_wars.Village.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class Resources_service {
    private final Game_config game_config;
    private final Buildings_config buildings_config;

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
            LocalDateTime new_update_time = last_update.plusSeconds((long) elapsed_intervals * interval);
            Map<Resource,Integer> interval_production = get_village_interval_resource_production(village);
            interval_production.forEach((resource, amount) -> {
                village.getResources().update_resource(amount,resource);
            });
            village.getResources().setResource_last_update(new_update_time);

        }
    }
    public Map<Resource,Integer> get_village_interval_resource_production(Village village){
        Map<Resource,Integer> resources = new HashMap<>();

        Map<Building_type,Resource> building_to_resource = get_building_to_resource_map();
        building_to_resource.forEach((building, resource) -> {
            int level = village.getBuildings().getOrDefault(building,0);
            int production = level * buildings_config.getResource_production_per_level();
            if(village.getSpecialty() != null && village.getSpecialty().name().equals(resource.name()))
                production += (int) (production * buildings_config.getResource_production_extra_bonus());
            resources.put(resource,production);
        });

        return resources;
    }
    public static Map<Building_type, Resource> get_building_to_resource_map(){
        return Map.of(
                Building_type.LumberMill, Resource.Wood,
                Building_type.Quarry, Resource.Stone,
                Building_type.Mine, Resource.Iron,
                Building_type.Marketplace, Resource.Gold);
    }

}
