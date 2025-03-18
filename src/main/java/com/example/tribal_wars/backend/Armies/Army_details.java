package com.example.tribal_wars.backend.Armies;

import com.example.tribal_wars.backend.Enums.Unit_type;
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
    private Integer spearman = 0;
    private Integer swordsman = 0;
    private Integer axeman = 0;
    private Integer archer = 0;
    private Integer scout = 0;
    private Integer light_cavalry = 0;
    private Integer heavy_cavalry = 0;
    private Integer ram = 0;
    private Integer catapult = 0;
    private Integer noble = 0;

    public static Map<Unit_type, Integer> get_army_map(Army_details army) {
        Map<Unit_type, Integer> army_map = new EnumMap<>(Unit_type.class);

        army_map.put(Unit_type.Spearman, army.getSpearman() != null ? army.getSpearman() : 0);
        army_map.put(Unit_type.Swordsman, army.getSwordsman() != null ? army.getSwordsman() : 0);
        army_map.put(Unit_type.Axeman, army.getAxeman() != null ? army.getAxeman() : 0);
        army_map.put(Unit_type.Archer, army.getArcher() != null ? army.getArcher() : 0);
        army_map.put(Unit_type.Scout, army.getScout() != null ? army.getScout() : 0);
        army_map.put(Unit_type.Light_cavalry, army.getLight_cavalry() != null ? army.getLight_cavalry() : 0);
        army_map.put(Unit_type.Heavy_cavalry, army.getHeavy_cavalry() != null ? army.getHeavy_cavalry() : 0);
        army_map.put(Unit_type.Ram, army.getRam() != null ? army.getRam() : 0);
        army_map.put(Unit_type.Catapult, army.getCatapult() != null ? army.getCatapult() : 0);
        army_map.put(Unit_type.Noble, army.getNoble() != null ? army.getNoble() : 0);

        return army_map;
    }


    public Army_details merge(Army_details other) {
        return new Army_details(
                (this.spearman != null ? this.spearman : 0) + (other.spearman != null ? other.spearman : 0),
                (this.swordsman != null ? this.swordsman : 0) + (other.swordsman != null ? other.swordsman : 0),
                (this.axeman != null ? this.axeman : 0) + (other.axeman != null ? other.axeman : 0),
                (this.archer != null ? this.archer : 0) + (other.archer != null ? other.archer : 0),
                (this.scout != null ? this.scout : 0) + (other.scout != null ? other.scout : 0),
                (this.light_cavalry != null ? this.light_cavalry : 0) + (other.light_cavalry != null ? other.light_cavalry : 0),
                (this.heavy_cavalry != null ? this.heavy_cavalry : 0) + (other.heavy_cavalry != null ? other.heavy_cavalry : 0),
                (this.ram != null ? this.ram : 0) + (other.ram != null ? other.ram : 0),
                (this.catapult != null ? this.catapult : 0) + (other.catapult != null ? other.catapult : 0),
                (this.noble != null ? this.noble : 0) + (other.noble != null ? other.noble : 0)
        );
    }


    @Override
    public String toString() {
        return "Army_details{" +
                "spearman=" + spearman +
                ", swordsman=" + swordsman +
                ", axeman=" + axeman +
                ", archer=" + archer +
                ", scout=" + scout +
                ", light_cavalry=" + light_cavalry +
                ", heavy_cavalry=" + heavy_cavalry +
                ", ram=" + ram +
                ", catapult=" + catapult +
                ", noble=" + noble +
                '}';
    }
}
