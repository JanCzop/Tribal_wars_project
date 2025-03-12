package com.example.tribal_wars.Armies.Army_commands;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Village.Village;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Army_command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "origin_village_x", referencedColumnName = "village_x"),
            @JoinColumn(name = "origin_village_y", referencedColumnName = "village_y")
    })
    private Village origin_village;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "origin_village_x", referencedColumnName = "village_x"),
            @JoinColumn(name = "origin_village_y", referencedColumnName = "village_y")
    })
    private Village target_village;


     */


    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Embedded
    Army_details army_details;


}
