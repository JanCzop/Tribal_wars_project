package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Enums.Specialty;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Armies.Army_village.Army;
import com.example.tribal_wars.Village.Construction.Construction;
import com.example.tribal_wars.Village.Recruitment.Recruitment;
import com.example.tribal_wars.Village.Resources.Village_resources;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Entity
@Getter @Setter
public class Village {
    public Village(Coordinates coordinates){
        this.coordinates = coordinates;
        this.buildings = new EnumMap<Building_type, Integer>(Building_type.class);
        this.resources = new Village_resources();
    }
    public Village(){
        this.buildings = new EnumMap<Building_type, Integer>(Building_type.class);
        this.resources = new Village_resources();
    }

    @EmbeddedId
    private Coordinates coordinates;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;



    @OneToMany(mappedBy = "village", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Army> armies;
    @OneToOne
    @JoinColumn(name = "local_army", unique = true)
    private Army local_army;


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
    private Map<Building_type, Integer> buildings;

    @Embedded
    @Column(nullable = false)
    private Village_resources resources = new Village_resources();

    @OneToOne(mappedBy = "village", cascade = CascadeType.ALL, orphanRemoval = true)
    private Construction construction;
    @OneToMany(mappedBy = "village", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recruitment> recruitment;

    private Specialty specialty;

}
