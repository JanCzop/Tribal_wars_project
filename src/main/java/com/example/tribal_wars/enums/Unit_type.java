package com.example.tribal_wars.enums;

import lombok.Getter;

import java.util.Map;

@Getter
public enum Unit_type {
    Spearman(Unit_subtype.Infantry, 50, 30, Map.of(Resource.Wood, 50, Resource.Stone, 30, Resource.Iron, 10)),
    Swordsman(Unit_subtype.Infantry, 70, 40, Map.of(Resource.Wood, 30, Resource.Stone, 30, Resource.Iron, 70)),
    Axeman(Unit_subtype.Infantry, 80, 50, Map.of(Resource.Wood, 60, Resource.Stone, 30, Resource.Iron, 40)),
    Archer(Unit_subtype.Infantry, 60, 35, Map.of(Resource.Wood, 100, Resource.Stone, 30, Resource.Iron, 60)),
    Scout(Unit_subtype.Infantry, 40, 25, Map.of(Resource.Wood, 50, Resource.Stone, 50, Resource.Iron, 20)),
    Light_cavalry(Unit_subtype.Cavalry, 90, 60, Map.of(Resource.Wood, 125, Resource.Stone, 100, Resource.Iron, 250)),
    Heavy_cavalry(Unit_subtype.Cavalry, 120, 80, Map.of(Resource.Wood, 200, Resource.Stone, 150, Resource.Iron, 300)),
    Ram(Unit_subtype.Siege, 150, 100, Map.of(Resource.Wood, 300, Resource.Stone, 200, Resource.Iron, 200)),
    Catapult(Unit_subtype.Siege, 200, 120, Map.of(Resource.Wood, 320, Resource.Stone, 400, Resource.Iron, 100)),
    Noble(Unit_subtype.Infantry, 150, 90, Map.of(Resource.Wood, 40000, Resource.Stone, 50000, Resource.Iron, 50000));

    private final Unit_subtype unit_subtype;
    private final Integer recruitment_time;
    private final Integer overall_power;
    private final Map<Resource, Integer> cost;

    Unit_type(Unit_subtype unit_subtype, Integer overall_power, Integer recruitment_time, Map<Resource, Integer> cost) {
        this.unit_subtype = unit_subtype;
        this.overall_power = overall_power;
        this.recruitment_time = recruitment_time;
        this.cost = cost;
    }

    public enum Unit_subtype {
        Infantry, Cavalry, Siege
    }

    public Map<Resource, Integer> getCost() {
        return cost;
    }
}

