package com.example.tribal_wars.Village.Buildings;

import lombok.Getter;

public enum Building_type {
    TEST_DEFAULT_BUILDING,
    TEST_CONCRETE_BUILDING(100,200,250,20)
    ;

    private static final int DEFAULT_COST = 100;
    @Getter
    private final int wood_cost;
    @Getter
    private final int stone_cost;
    @Getter
    private final int iron_cost;
    @Getter
    private final int build_time;

    Building_type(int wood_cost, int stone_cost, int iron_cost, int build_time) {
        this.wood_cost = wood_cost;
        this.stone_cost = stone_cost;
        this.iron_cost = iron_cost;
        this.build_time = build_time;
    }

    Building_type() {
        this.wood_cost = DEFAULT_COST;
        this.stone_cost = DEFAULT_COST;
        this.iron_cost = DEFAULT_COST;
        this.build_time = DEFAULT_COST;
    }
}
