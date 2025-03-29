package com.example.tribal_wars.entities.village.embbed;

import com.example.tribal_wars.entities.army.embbed.Army_details;
import com.example.tribal_wars.entities.village.Village;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Army_details army_details;
    @Column(nullable = false)
    private LocalDateTime recruitment_start_time;
    @Column(nullable = false)
    private LocalDateTime recruitment_end_time;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "village_x", referencedColumnName = "x"),
            @JoinColumn(name = "village_y", referencedColumnName = "y")
    })
    @JsonIgnore
    private Village village;

    public Recruitment(Army_details army_details, LocalDateTime recruitment_start_time, LocalDateTime recruitment_end_time, Village village) {
        this.army_details = army_details;
        this.recruitment_start_time = recruitment_start_time;
        this.recruitment_end_time = recruitment_end_time;
        this.village = village;
    }


}
