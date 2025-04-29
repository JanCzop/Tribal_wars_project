package com.example.tribal_wars.entities.village.embbed;

import com.example.tribal_wars.enums.Resource;
import com.example.tribal_wars.exceptions.Exc_not_enough_resources;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Village_resources {

    private Integer current_gold = 0;
    private Integer current_iron = 0;
    private Integer current_stone = 0;
    private Integer current_wood = 0;
    private Integer resources_capacity = 1000;
    private Integer cache_capacity = 1000;
    private Integer gold_capacity = 1000;
    @Column(nullable = false)
    private LocalDateTime resource_last_update = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS); //truncatedTo(ChronoUnit.HOURS);
    // TODO: NOW RESOURCES UPDATE EVERY INTERVAL FOR EACH VILLAGE TIMER INDIVIDUALLY, IT WILL BE NECCESSARY TO HANDLE IT LATER


    @Override
    public String toString() {
        return "Village_resources{" +
                "current_gold=" + current_gold +
                ", current_iron=" + current_iron +
                ", current_stone=" + current_stone +
                ", current_wood=" + current_wood +
                ", resources_capacity=" + resources_capacity +
                ", cache_capacity=" + cache_capacity +
                ", gold_capacity=" + gold_capacity +
                ", resource_last_update=" + resource_last_update +
                '}';
    }

    public int get_resource(Resource resource_type) {
        return switch (resource_type) {
            case Gold -> this.current_gold;
            case Iron -> this.current_iron;
            case Stone -> this.current_stone;
            case Wood -> this.current_wood;
        };
    }


    public void update_resource(int resource_amount, Resource resource_type){
        switch (resource_type){
            case Gold -> {
                int newVal = this.current_gold + resource_amount;
                if (newVal < 0) throw new Exc_not_enough_resources("Not enough gold");
                setCurrent_gold(newVal);
            }
            case Iron -> {
                int newVal = this.current_iron + resource_amount;
                if (newVal < 0) throw new Exc_not_enough_resources("Not enough iron");
                setCurrent_iron(newVal);
            }
            case Stone -> {
                int newVal = this.current_stone + resource_amount;
                if (newVal < 0) throw new Exc_not_enough_resources("Not enough stone");
                setCurrent_stone(newVal);
            }
            case Wood -> {
                int newVal = this.current_wood + resource_amount;
                if (newVal < 0) throw new Exc_not_enough_resources("Not enough wood");
                setCurrent_wood(newVal);
            }
        }
    }

    public void update_all_resources_specifically(int wood, int stone, int iron, int gold) {
        update_resource(wood, Resource.Wood);
        update_resource(stone, Resource.Stone);
        update_resource(iron, Resource.Iron);
        update_resource(gold, Resource.Gold);
    }
    public void update_all_resources(int amount){
        update_all_resources_specifically(amount,amount,amount,amount);
    }

    private static final int MINIMUM_RESOURCES = 0;
    private int validate_current_resource(int current_resource, int capacity){
        if(current_resource <= MINIMUM_RESOURCES) return MINIMUM_RESOURCES;
        else return Math.min(current_resource, capacity);
    }
    public void setCurrent_gold(int gold) {
        this.current_gold = validate_current_resource(gold,gold_capacity);
    }

    public void setCurrent_iron(int iron) {
        this.current_iron = validate_current_resource(iron,resources_capacity);
    }

    public void setCurrent_stone(int stone) {
        this.current_stone = validate_current_resource(stone,resources_capacity);
    }

    public void setCurrent_wood(int wood) {
        this.current_wood = validate_current_resource(wood,resources_capacity);
    }
}
