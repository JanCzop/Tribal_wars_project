package com.example.tribal_wars.controllers.private_endpoints.DTOs;

import jakarta.validation.constraints.NotNull;

public record Change_village_name_DTO(
        @NotNull(message = "name is required")  String name
){}
