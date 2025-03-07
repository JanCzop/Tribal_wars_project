package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Resource;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Village_resources {

    private int current_gold;
    private int current_iron;
    private int current_stone;
    private int current_wood;
    @Setter
    private int resources_capacity;
    @Setter
    private int cache_capacity;
    @Setter
    private int gold_capacity;
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime resource_last_update;
    @PrePersist @PreUpdate
    public void setResource_last_update() {
        this.resource_last_update = LocalDateTime.now();
    }



    public void update_resource(int resource_amount, Resource resource_type){
        switch (resource_type){
            case GOLD -> setCurrent_gold(this.current_gold + resource_amount);
            case IRON -> setCurrent_iron(this.current_iron + resource_amount);
            case STONE -> setCurrent_stone(this.current_stone + resource_amount);
            case WOOD -> setCurrent_wood(this.current_wood + resource_amount);
        }
    }
    public void update_all_resources(int wood, int stone, int iron, int gold) {
        setCurrent_wood(this.current_wood + wood);
        setCurrent_stone(this.current_stone + stone);
        setCurrent_iron(this.current_iron + iron);
        setCurrent_gold(this.current_gold + gold);
    }

    private static final int MINIMUM_RESOURCES = 0;
    private int validate_current_resource(int current_resource, int capacity){
        if(current_resource <= MINIMUM_RESOURCES) return MINIMUM_RESOURCES;
        else return Math.min(current_resource, capacity);
    }
    public void setCurrent_gold(int current_gold) {
        this.current_gold = validate_current_resource(current_gold,gold_capacity);
    }

    public void setCurrent_iron(int current_iron) {
        this.current_iron = validate_current_resource(current_iron,resources_capacity);
    }

    public void setCurrent_stone(int current_stone) {
        this.current_stone = validate_current_resource(current_stone,resources_capacity);
    }

    public void setCurrent_wood(int current_wood) {
        this.current_wood = validate_current_resource(current_wood,resources_capacity);
    }
}
