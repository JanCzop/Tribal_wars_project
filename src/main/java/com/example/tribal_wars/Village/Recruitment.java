package com.example.tribal_wars.Village;

import com.example.tribal_wars.Enums.Building_type;
import com.example.tribal_wars.Enums.Unit_type;
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
@Getter
@Setter
public class Recruitment {
    @Enumerated(EnumType.STRING)
    private Unit_type unit_type;
    private LocalDateTime recruitment_start_time;
    private LocalDateTime recruitment_end_time;
}
