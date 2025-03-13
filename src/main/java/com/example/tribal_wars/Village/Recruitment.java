package com.example.tribal_wars.Village;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Enums.Unit_type;
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
    private Long id;
    @Embedded
    private Army_details army_details;
    @Column(nullable = false)
    private LocalDateTime recruitment_start_time;
    @Column(nullable = false)
    private LocalDateTime recruitment_end_time;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "village_x", referencedColumnName = "x"),
            @JoinColumn(name = "village_y", referencedColumnName = "y")
    })
    private Village village;
}
