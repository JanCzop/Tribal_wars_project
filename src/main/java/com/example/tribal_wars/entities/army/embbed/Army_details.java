package com.example.tribal_wars.entities.army.embbed;

import com.example.tribal_wars.enums.Resource;
import com.example.tribal_wars.enums.Unit_type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Embeddable
@Access(AccessType.FIELD)
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

    public Map<Unit_type, Integer> get_army_map() {
        Map<Unit_type, Integer> army_map = new EnumMap<>(Unit_type.class);

        army_map.put(Unit_type.Spearman, this.getSpearman() != null ? this.getSpearman() : 0);
        army_map.put(Unit_type.Swordsman, this.getSwordsman() != null ? this.getSwordsman() : 0);
        army_map.put(Unit_type.Axeman, this.getAxeman() != null ? this.getAxeman() : 0);
        army_map.put(Unit_type.Archer, this.getArcher() != null ? this.getArcher() : 0);
        army_map.put(Unit_type.Scout, this.getScout() != null ? this.getScout() : 0);
        army_map.put(Unit_type.Light_cavalry, this.getLight_cavalry() != null ? this.getLight_cavalry() : 0);
        army_map.put(Unit_type.Heavy_cavalry, this.getHeavy_cavalry() != null ? this.getHeavy_cavalry() : 0);
        army_map.put(Unit_type.Ram, this.getRam() != null ? this.getRam() : 0);
        army_map.put(Unit_type.Catapult, this.getCatapult() != null ? this.getCatapult() : 0);
        army_map.put(Unit_type.Noble, this.getNoble() != null ? this.getNoble() : 0);

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

    public Map<Resource, Integer> get_total_cost() {
        Map<Resource, Integer> totalCost = new EnumMap<>(Resource.class);

        for (Map.Entry<Unit_type, Integer> entry : get_army_map().entrySet()) {
            Unit_type unit = entry.getKey();
            int count = entry.getValue();

            unit.getCost().forEach((resource, costPerUnit) -> {
                totalCost.merge(resource, costPerUnit * count, Integer::sum);
            });
        }

        return totalCost;
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
