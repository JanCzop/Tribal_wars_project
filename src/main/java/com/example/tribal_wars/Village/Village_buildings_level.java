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
public class Village_buildings_level {
    private Integer town_hall;
    private Integer barracks;
    private Integer stable;
    private Integer blacksmith;
    private Integer farms;
    private Integer mine;
    private Integer quarry;
    private Integer lumber_mill;
    private Integer granary;
    private Integer cache;
    private Integer marketplace;
    private Integer city_walls;
    private Integer castle;


}
