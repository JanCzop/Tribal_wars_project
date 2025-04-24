package com.example.tribal_wars.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "game.village")
@Getter
@Setter
public class Village_config {
    private int base_resource_capacity;
    private int base_gold_capacity;
}
