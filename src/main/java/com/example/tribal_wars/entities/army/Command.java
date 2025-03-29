package com.example.tribal_wars.entities.army;

import com.example.tribal_wars.entities.army.embbed.Army_details;
import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.entities.village.Village;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "origin_village_x", referencedColumnName = "x"),
            @JoinColumn(name = "origin_village_y", referencedColumnName = "y")
    })
    @JsonIgnore
    private Village origin_village;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "target_village_x", referencedColumnName = "x"),
            @JoinColumn(name = "target_village_y", referencedColumnName = "y")
    })
    @JsonIgnore
    private Village target_village;



    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Embedded
    @Column(nullable = false)
    Army_details army_details;


}
