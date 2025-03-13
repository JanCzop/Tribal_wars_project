package com.example.tribal_wars.Armies.Army_village;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Village.Coordinates;
import com.example.tribal_wars.Village.Village;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor
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
    private Village village;




    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Embedded
    private Army_details army_details;


}
