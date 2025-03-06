package com.example.tribal_wars.Village;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Getter @Setter
public class Construction {
    @Enumerated(EnumType.STRING)
    private Building_types construction_building_type;
    private Integer construction_target_level;
    private LocalDateTime construction_start_time;
    private LocalDateTime construction_end_time;
}
