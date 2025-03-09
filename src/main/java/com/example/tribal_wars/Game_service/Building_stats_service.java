package com.example.tribal_wars.Game_service;

import com.example.tribal_wars.Enums.Building_types;
import com.example.tribal_wars.Village.Village;
import com.example.tribal_wars.Village.Village_buildings_level;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class Building_stats_service {

    public Map<Building_types, Integer> get_villages_buildings_levels(Village village){
        Map<Building_types, Integer> buildings_levels = new EnumMap<>(Building_types.class);
        Village_buildings_level buildingsLevel = village.getBuildings();
        buildings_levels.put(Building_types.TownHall, buildingsLevel.getTown_hall());
        buildings_levels.put(Building_types.Barracks, buildingsLevel.getBarracks());
        buildings_levels.put(Building_types.Stable, buildingsLevel.getStable());
        buildings_levels.put(Building_types.Blacksmith, buildingsLevel.getBlacksmith());
        buildings_levels.put(Building_types.Farms, buildingsLevel.getFarms());
        buildings_levels.put(Building_types.Mine, buildingsLevel.getMine());
        buildings_levels.put(Building_types.Quarry, buildingsLevel.getQuarry());
        buildings_levels.put(Building_types.LumberMill, buildingsLevel.getLumber_mill());
        buildings_levels.put(Building_types.Granary, buildingsLevel.getGranary());
        buildings_levels.put(Building_types.Cache, buildingsLevel.getCache());
        buildings_levels.put(Building_types.Marketplace, buildingsLevel.getMarketplace());
        buildings_levels.put(Building_types.CityWalls, buildingsLevel.getCity_walls());
        buildings_levels.put(Building_types.Castle, buildingsLevel.getCastle());

        return buildings_levels;
    }

}
