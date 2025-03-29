package com.example.tribal_wars.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "game.time")
@Getter @Setter
public class Game_config {
    private int resources_harvest_interval;
}
