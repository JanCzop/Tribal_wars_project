package com.example.tribal_wars.services.village;

import com.example.tribal_wars.config.Buildings_config;
import com.example.tribal_wars.config.Game_config;
import com.example.tribal_wars.entities.army.embbed.Army_details;
import com.example.tribal_wars.enums.Building_type;
import com.example.tribal_wars.enums.Resource;
import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.exceptions.Exc_not_enough_resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    public void ensure_affordable_and_pay(Village village, Building_type building, int level) {
        Map<Resource, Integer> costs = building.get_costs_for_level(level);
        for (Map.Entry<Resource, Integer> entry : costs.entrySet()) {
            int current = village.getResources().get_resource(entry.getKey());
            if (current < entry.getValue())
                throw new Exc_not_enough_resources("Not enough " + entry.getKey().name().toLowerCase());
        }
        costs.forEach((resource, amount) -> village.getResources().update_resource(-amount, resource));
    }

    public void ensure_affordable_and_pay(Village village, Army_details army) {
        Map<Resource, Integer> total_cost = army.get_total_cost();
        List<Resource> missing = new ArrayList<>();
        for (Map.Entry<Resource, Integer> entry : total_cost.entrySet()) {
            int available = village.getResources().get_resource(entry.getKey());
            if (available < entry.getValue()) {
                missing.add(entry.getKey());
            }
        }
        if (!missing.isEmpty()) {
            if (missing.size() == 1) {
                Resource res = missing.get(0);
                throw switch (res) {
                    case Wood -> new Exc_not_enough_resources("Not enough wood for recruitment");
                    case Stone -> new Exc_not_enough_resources("Not enough stone for recruitment");
                    case Iron -> new Exc_not_enough_resources("Not enough iron for recruitment");
                    case Gold -> new Exc_not_enough_resources("Not enough gold for recruitment");
                };
            } else throw new Exc_not_enough_resources("Not enough resources for recruitment");

        }
        total_cost.forEach((res, amount) -> village.getResources().update_resource(-amount, res));
    }




}
