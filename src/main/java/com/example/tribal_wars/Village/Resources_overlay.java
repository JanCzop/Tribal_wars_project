package com.example.tribal_wars.Village;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Resources_overlay {
    public enum Resource {
        WOOD, STONE, IRON, GOLD
    }

    private int current_wood;
    private int current_stone;
    private int current_iron;
    private int current_gold;

    private int resources_capacity;
    private int cache;
    private int gold_capacity;


}
