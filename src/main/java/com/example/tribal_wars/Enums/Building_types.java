package com.example.tribal_wars.Enums;

import lombok.Getter;

import java.util.Map;

@Getter
public enum Building_types {
    TownHall(Map.of(Resource.Wood, 200, Resource.Stone, 150, Resource.Iron, 100, Resource.Gold, 50), 60),
    Barracks(Map.of(Resource.Wood, 150, Resource.Stone, 100, Resource.Iron, 200, Resource.Gold, 75), 90),
    Stable(Map.of(Resource.Wood, 300, Resource.Stone, 200, Resource.Iron, 250, Resource.Gold, 100), 120),
    Blacksmith(Map.of(Resource.Wood, 250, Resource.Stone, 250, Resource.Iron, 300, Resource.Gold, 150), 150),
    Farms(Map.of(Resource.Wood, 100, Resource.Stone, 50, Resource.Iron, 30, Resource.Gold, 10), 30),
    Mine(Map.of(Resource.Wood, 50, Resource.Stone, 100, Resource.Iron, 150, Resource.Gold, 75), 45),
    Quarry(Map.of(Resource.Wood, 100, Resource.Stone, 200, Resource.Iron, 50, Resource.Gold, 30), 60),
    LumberMill(Map.of(Resource.Wood, 200, Resource.Stone, 100, Resource.Iron, 50, Resource.Gold, 25), 50),
    Granary(Map.of(Resource.Wood, 150, Resource.Stone, 150, Resource.Iron, 100, Resource.Gold, 50), 75),
    Cache(Map.of(Resource.Wood, 100, Resource.Stone, 50, Resource.Iron, 50, Resource.Gold, 25), 40),
    Marketplace(Map.of(Resource.Wood, 200, Resource.Stone, 150, Resource.Iron, 150, Resource.Gold, 100), 90),
    CityWalls(Map.of(Resource.Wood, 500, Resource.Stone, 1000, Resource.Iron, 300, Resource.Gold, 200), 180),
    Castle(Map.of(Resource.Wood, 1000, Resource.Stone, 2000, Resource.Iron, 1500, Resource.Gold, 500), 240);

    private final Map<Resource, Integer> base_cost;
    private final int base_construction_time;

    // TODO: ITS STATIC FOR ALL ENUMS NOW BUT CAN BE PARAMETRIZED EACH INDIVIDUALLY
    private static final double COST_MULTIPLIER = 1.5;
    private static final double TIME_MULTIPLIER = 2.1;

    Building_types(Map<Resource, Integer> baseCost, int baseConstructionTime) {
        this.base_cost = baseCost;
        this.base_construction_time = baseConstructionTime;
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
