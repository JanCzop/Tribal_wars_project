package com.example.tribal_wars.Armies.Army_village;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Village.Coordinates;
import com.example.tribal_wars.Village.Village;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@IdClass(Army.Army_id.class)
@Getter @Setter
public class Army {

    @Getter @Setter
    public static class Army_id implements Serializable{
        private Coordinates village;
        private Long player;
        public Army_id(Coordinates village, Long player) {
            this.village = village;
            this.player = player;
        }
        public Army_id() {
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Army_id armyId = (Army_id) o;
            return Objects.equals(village, armyId.village) && Objects.equals(player, armyId.player);
        }
        @Override
        public int hashCode() {
            return Objects.hash(village, player);
        }
    }


    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "village_x", referencedColumnName = "x"),
            @JoinColumn(name = "village_y", referencedColumnName = "y")
    })
    private Village village;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Embedded
    private Army_details army_details;


}
