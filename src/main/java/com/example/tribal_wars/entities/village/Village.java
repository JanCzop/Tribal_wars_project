package com.example.tribal_wars.entities.village;

import com.example.tribal_wars.config.Buildings_config;
import com.example.tribal_wars.entities.army.Command;
import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.entities.army.embbed.Army_details;
import com.example.tribal_wars.entities.army.Army;
import com.example.tribal_wars.enums.Building_type;
import com.example.tribal_wars.enums.Specialty;
import com.example.tribal_wars.entities.village.embbed.Construction;
import com.example.tribal_wars.entities.village.embbed.Coordinates;
import com.example.tribal_wars.entities.village.embbed.Recruitment;
import com.example.tribal_wars.entities.village.embbed.Village_resources;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Village {


    @EmbeddedId
    private Coordinates coordinates;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private String name;
    @OneToMany(mappedBy = "village", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Army> armies;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "local_army", unique = true)
    private Army local_army;

    @OneToMany(mappedBy = "origin_village", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Command> army_commands;


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
    @Enumerated(EnumType.STRING)
    private Specialty specialty = Specialty.None;


}
