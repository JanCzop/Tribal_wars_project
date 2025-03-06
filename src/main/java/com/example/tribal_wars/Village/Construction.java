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
    private Building_types building_types;
    private Integer target_level;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
}
