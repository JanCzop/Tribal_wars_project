package com.example.tribal_wars.controllers.private_endpoints.DTOs;

import com.example.tribal_wars.enums.Building_type;
import jakarta.validation.constraints.NotNull;

public record Construct_building_DTO(
        @NotNull(message = "building_type is required") Building_type building_type
){}
