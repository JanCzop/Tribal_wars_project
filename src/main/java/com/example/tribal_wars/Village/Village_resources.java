package com.example.tribal_wars.Village;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Village_resources {

    private int current_wood;
    private int current_stone;
    private int current_iron;
    private int current_gold;
    private int resources_capacity;
    private int cache_capacity;
    private int gold_capacity;
    private LocalDateTime resource_last_update;


}
