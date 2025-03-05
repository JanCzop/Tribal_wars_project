package com.example.tribal_wars.Village;

import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Village.Buildings.Building;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@NoArgsConstructor
public class Village {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @Getter @Setter
    private Player player_owner;

    @ElementCollection
    @CollectionTable(name = "village_buildings", joinColumns = @JoinColumn(name = "village_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "building")
    @Column(name = "level")
    @Getter @Setter
    private Map<Building, Integer> buildings;

}
