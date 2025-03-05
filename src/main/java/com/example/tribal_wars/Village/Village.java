package com.example.tribal_wars.Village;

import com.example.tribal_wars.Village.Buildings.Building;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
public class Village {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private long player_id;
    @ElementCollection
    @CollectionTable(name = "village_buildings", joinColumns = @JoinColumn(name = "village_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "level")
    @Getter @Setter
    private Map<Building, Integer> buildings;

    public Village(long id, long player_id, Map<Building, Integer> buildings) {
        this.id = id;
        this.player_id = player_id;
        this.buildings = buildings;
    }

    public Village() {
    }
}
