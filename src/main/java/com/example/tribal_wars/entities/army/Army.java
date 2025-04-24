package com.example.tribal_wars.entities.army;

import com.example.tribal_wars.entities.army.embbed.Army_details;
import com.example.tribal_wars.entities.player.Player;
import com.example.tribal_wars.entities.village.Village;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter @Setter
public class Army {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "village_x", referencedColumnName = "x"),
            @JoinColumn(name = "village_y", referencedColumnName = "y")
    })
    @JsonIgnore
    private Village village;



    /*
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

     */

    @Embedded
    @Column(nullable = false)
    private Army_details army_details;


    public Army(){
        this.army_details = new Army_details();
    }



}
