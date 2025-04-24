package com.example.tribal_wars.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "game.buildings")
@Getter
@Setter
public class Buildings_config {
    private int resource_production_per_level;
    private double resource_production_extra_bonus;
    private int granary_capacity_per_level;
    /*
    private int building_base_cost;
    private double building_cost_per_level_multiplier;
    private int building_base_construction_time;
    private double building_construction_time_per_level_multiplier;

     */
}
