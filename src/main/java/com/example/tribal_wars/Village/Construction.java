package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Building_type construction_building_type;
    @Column(nullable = false)
    private LocalDateTime construction_start_time;
    @Column(nullable = false)
    private LocalDateTime construction_end_time;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "village_x", referencedColumnName = "x"),
            @JoinColumn(name = "village_y", referencedColumnName = "y")
    })
    private Village village;

    public Construction(Building_type construction_building_type, LocalDateTime construction_start_time, LocalDateTime construction_end_time, Village village) {
        this.construction_building_type = construction_building_type;
        this.construction_start_time = construction_start_time;
        this.construction_end_time = construction_end_time;
        this.village = village;
    }
}
