package com.example.tribal_wars.Game_service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Construction_service {
    @Getter
    private final Building_stats_service building_stats_service;
    @Autowired
    public Construction_service(Building_stats_service building_stats_service) {
        this.building_stats_service = building_stats_service;
    }
}
