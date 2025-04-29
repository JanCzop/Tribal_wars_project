package com.example.tribal_wars.controllers.private_endpoints.DTOs;

import com.example.tribal_wars.entities.army.embbed.Army_details;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record Recruit_army_DTO(
        @NotNull(message = "army_details must not be null")
        @Valid Army_details army_details
) {}
