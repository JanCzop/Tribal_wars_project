package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Construction {
    @Enumerated(EnumType.STRING)
    private Building_type construction_building_type;
    private LocalDateTime construction_start_time;
    private LocalDateTime construction_end_time;
}
