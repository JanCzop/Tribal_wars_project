package com.example.tribal_wars.Armies;

import com.example.tribal_wars.Enums.Unit_type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Army_details {
    private Integer spearman;
    private Integer swordsman;
    private Integer axeman;
    private Integer archer;
    private Integer scout;
    private Integer light_cavalry;
    private Integer heavy_cavalry;
    private Integer ram;
    private Integer catapult;
    private Integer noble;

    public static Map<Unit_type,Integer> get_army_map(Army_details army){
        Map<Unit_type, Integer> army_map = new EnumMap<>(Unit_type.class);

        army_map.put(Unit_type.Spearman, army.getSpearman());
        army_map.put(Unit_type.Swordsman, army.getSwordsman());
        army_map.put(Unit_type.Axeman, army.getAxeman());
        army_map.put(Unit_type.Archer, army.getArcher());
        army_map.put(Unit_type.Scout, army.getScout());
        army_map.put(Unit_type.Light_cavalry, army.getLight_cavalry());
        army_map.put(Unit_type.Heavy_cavalry, army.getHeavy_cavalry());
        army_map.put(Unit_type.Ram, army.getRam());
        army_map.put(Unit_type.Catapult, army.getCatapult());
        army_map.put(Unit_type.Noble, army.getNoble());

        return army_map;
    }

}
