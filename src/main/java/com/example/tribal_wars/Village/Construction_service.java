package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
@Getter
public class Construction_service {
    // TODO: DECONSTRUCTION

    private static final int TEST_ACCELERATION = 10;
    private final Construction_repository construction_repository;
    @Autowired
    public Construction_service(Construction_repository construction_repository) {
        this.construction_repository = construction_repository;
    }

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
        Construction construction = new Construction(building, now, construction_end, village);
        this.construction_repository.save(construction);
        village.setConstruction(construction);
    }
    public void check_construction(Village village){
        if(village.getConstruction() != null &&
                village.getConstruction().getConstruction_end_time().isBefore(LocalDateTime.now()))
            end_construction(village);
    }
    public void stop_construction(Village village){
        this.construction_repository.delete(village.getConstruction());
        village.setConstruction(null);
    }
    public void end_construction(Village village){
        if(village.getConstruction() == null) return;
        upgrade_building(
                village.getConstruction().getConstruction_building_type(),
                village.getBuildings());
        this.construction_repository.delete(village.getConstruction());
        stop_construction(village);
    }

    public void upgrade_building(Building_type building, Map<Building_type,Integer> village_buildings){
        village_buildings.compute(building, (key,level) -> (level == null) ? 1 : level + 1);
    }
    public void downgrade_building(Building_type building, Map<Building_type,Integer> village_buildings){
        village_buildings.compute(building, (key,level) -> (level == null || level < 1) ? null : level - 1);
    }

}
