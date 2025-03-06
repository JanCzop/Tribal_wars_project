package com.example.tribal_wars.Game_service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "game")
@Getter @Setter
public class Game_config {
    private int resources_harvest_interval;
}
