package com.example.tribal_wars.Village.Buildings;

import lombok.Getter;
import lombok.Setter;

public class Building {
    @Getter
    private final Building_type type;
    @Getter @Setter
    private int level;

    public Building(Building_type type, int level) {
        this.type = type;
        this.level = level;
    }

    public Building() {
        this.type = Building_type.TEST_DEFAULT_BUILDING;
    }
}
