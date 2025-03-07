package com.example.tribal_wars.Config;

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
}
