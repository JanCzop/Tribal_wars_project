package com.example.tribal_wars.backend.Enums;

import lombok.Getter;

import java.util.Map;

@Getter
public enum Building_type {
    TownHall(Map.of(Resource.Wood, 200, Resource.Stone, 150, Resource.Iron, 100, Resource.Gold, 50), 60, true),
    Barracks(Map.of(Resource.Wood, 150, Resource.Stone, 100, Resource.Iron, 200, Resource.Gold, 75), 90, false),
    Stable(Map.of(Resource.Wood, 300, Resource.Stone, 200, Resource.Iron, 250, Resource.Gold, 100), 120, false),
    Blacksmith(Map.of(Resource.Wood, 250, Resource.Stone, 250, Resource.Iron, 300, Resource.Gold, 150), 150,false),
    Farms(Map.of(Resource.Wood, 100, Resource.Stone, 50, Resource.Iron, 30, Resource.Gold, 10), 30, true),
    Mine(Map.of(Resource.Wood, 50, Resource.Stone, 100, Resource.Iron, 150, Resource.Gold, 75), 45, true),
    Quarry(Map.of(Resource.Wood, 100, Resource.Stone, 200, Resource.Iron, 50, Resource.Gold, 30), 60, true),
    LumberMill(Map.of(Resource.Wood, 200, Resource.Stone, 100, Resource.Iron, 50, Resource.Gold, 25), 50, true),
    Granary(Map.of(Resource.Wood, 150, Resource.Stone, 150, Resource.Iron, 100, Resource.Gold, 50), 75, true),
    Cache(Map.of(Resource.Wood, 100, Resource.Stone, 50, Resource.Iron, 50, Resource.Gold, 25), 40, false),
    Marketplace(Map.of(Resource.Wood, 200, Resource.Stone, 150, Resource.Iron, 150, Resource.Gold, 100), 90, false),
    CityWalls(Map.of(Resource.Wood, 500, Resource.Stone, 1000, Resource.Iron, 300, Resource.Gold, 200), 180, false),
    Castle(Map.of(Resource.Wood, 1000, Resource.Stone, 2000, Resource.Iron, 1500, Resource.Gold, 500), 240, false);

    private final Map<Resource, Integer> base_cost;
    private final int base_construction_time;

    // TODO: ITS STATIC FOR ALL ENUMS NOW BUT CAN BE PARAMETRIZED EACH INDIVIDUALLY
    private static final double COST_MULTIPLIER = 1.5;
    private static final double TIME_MULTIPLIER = 2.1;

    private final boolean necessary_building;
    public static final int MINIMUM_NECESSARY_BUILDING_LEVEL = 1;
    public static final int MINIMUM_UNNECESSARY_BUILDING_LEVEL = 0;

    // TODO: PARAMETRIZE THIS INDIVIDUALLY
    private final int max_level = 10;

    Building_type(Map<Resource, Integer> baseCost, int baseConstructionTime, boolean necessary_building) {
        this.base_cost = baseCost;
        this.base_construction_time = baseConstructionTime;
        this.necessary_building = necessary_building;
    }

    public Map<Resource, Integer> get_costs_for_level(int level) {
        return base_cost.entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (int) (entry.getValue() * Math.pow(COST_MULTIPLIER, level - 1))
                ));
    }

    public int get_time_for_level(int level) {
        return (int) (base_construction_time * Math.pow(TIME_MULTIPLIER, level - 1));
    }
}
