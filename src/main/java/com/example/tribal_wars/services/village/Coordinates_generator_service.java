package com.example.tribal_wars.services.village;

import com.example.tribal_wars.entities.village.embbed.Coordinates;
import com.example.tribal_wars.repositories.Village_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Coordinates_generator_service {
    private static final int MAX_ATTEMPTS = 1000;

    private final Village_repository village_repository;
    private final Random random = new Random();
    @Autowired
    public Coordinates_generator_service(Village_repository villageRepository) {
        village_repository = villageRepository;
    }

    // TODO: COORDS ARE SEMI-GROWTH-RANDOMIZED BUT SHOULD BE CONFIGURED WITH STRATEGY.

    public Coordinates generate_coordinates() {
        if (village_repository.count() == 0) return new Coordinates(0, 0);

        int minX = village_repository.findMinX().orElse(0);
        int maxX = village_repository.findMaxX().orElse(0);
        int minY = village_repository.findMinY().orElse(0);
        int maxY = village_repository.findMaxY().orElse(0);

        for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
            int x = pick_edge_val(minX, maxX);
            int y = pick_edge_val(minY, maxY);
            Coordinates coords = new Coordinates(x, y);

            if (!village_repository.existsByCoordinates(coords)) return coords;
        }

        throw new IllegalStateException("Nie można znaleźć wolnych współrzędnych");
    }

    private int pick_edge_val(int min, int max) {
        return switch (random.nextInt(3)) {
            case 0 -> min - 1;     // left/up
            case 1 -> max + 1;     // right/down
            default -> random.nextInt((max - min) + 1) + min; // middle
        };
    }

}
