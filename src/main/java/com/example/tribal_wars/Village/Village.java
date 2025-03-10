package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Player.Player;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Village {
    public Village(Coordinates coordinates){
        this.coordinates = coordinates;
        this.buildings = new EnumMap<Building_type, Integer>(Building_type.class);
        this.resources = new Village_resources();
    }

    @EmbeddedId
    @Getter
    private Coordinates coordinates;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonBackReference
    @Getter @Setter
    private Player player;

    @ElementCollection
    @CollectionTable(
            name = "village_buildings",
            joinColumns = {
                    @JoinColumn(name = "village_x", referencedColumnName = "x"),
                    @JoinColumn(name = "village_y", referencedColumnName = "y")
            }
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "building")
    @Column(name = "level")
    @Getter @Setter
    private Map<Building_type, Integer> buildings;

    /* LEGACY EMBEDDED BUILDINGS

    @Embedded
    @Column(nullable = false)
    @Getter @Setter
    private Village_buildings_level buildings = new Village_buildings_level();

     */

    @Embedded
    @Column(nullable = false)
    @Getter @Setter
    private Village_resources resources = new Village_resources();

    @Embedded
    @Getter @Setter
    private Construction construction;

}
