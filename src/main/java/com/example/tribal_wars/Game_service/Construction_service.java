package com.example.tribal_wars.Game_service;

import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Village.Construction;
import com.example.tribal_wars.Village.Village;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class Construction_service {

    private static final int TEST_ACCELERATION = 10;

    public boolean is_construction_viable(Building_type building, Village village){
        if(village.getConstruction() != null) return false;
        Integer current_level = village.getBuildings().get(building);
        if(current_level == null) return true;
        return current_level <= building.getMax_level();
    }
    public boolean is_deconstruction_viable(Building_type building, Village village){
        //if(village.getConstruction() != null) return false;
        Integer current_level = village.getBuildings().get(building);
        if(current_level == null) return false;
        return building.isNecessary_building() ?
                Building_type.MINIMUM_NECESSARY_BUILDING_LEVEL < current_level :
                Building_type.MINIMUM_UNNECESSARY_BUILDING_LEVEL < current_level
                ;
    }

    public void start_construction(Village village, Building_type building){
        if(village.getConstruction() != null) return;
        int constructed_building_level =
                village.getBuildings().getOrDefault(building,1) + 1;
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime construction_end =
                now.plus(building.get_time_for_level(constructed_building_level)/TEST_ACCELERATION, ChronoUnit.SECONDS).truncatedTo(ChronoUnit.SECONDS);
        village.setConstruction(new Construction(building, now, construction_end));
    }
    public void update_construction(Village village){
        if(village.getConstruction() != null &&
                village.getConstruction().getConstruction_end_time().isBefore(LocalDateTime.now()))
            end_construction(village);
    }
    public void stop_construction(Village village){
        village.setConstruction(null);
    }
    public void end_construction(Village village){
        if(village.getConstruction() == null) return;
        upgrade_building(
                village.getConstruction().getConstruction_building_type(),
                village.getBuildings());
        stop_construction(village);
    }

    public void upgrade_building(Building_type building, Map<Building_type,Integer> village_buildings){
        village_buildings.compute(building, (key,level) -> (level == null) ? 1 : level + 1);
    }
    public void downgrade_building(Building_type building, Map<Building_type,Integer> village_buildings){
        village_buildings.compute(building, (key,level) -> (level == null || level < 1) ? null : level - 1);
    }

}
