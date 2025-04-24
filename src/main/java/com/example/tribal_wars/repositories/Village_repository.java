package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.entities.village.embbed.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Village_repository extends JpaRepository<Village, Coordinates> {
    @Query("SELECT MIN(v.coordinates.x) FROM Village v")
    Optional<Integer> findMinX();

    @Query("SELECT MAX(v.coordinates.x) FROM Village v")
    Optional<Integer> findMaxX();

    @Query("SELECT MIN(v.coordinates.y) FROM Village v")
    Optional<Integer> findMinY();

    @Query("SELECT MAX(v.coordinates.y) FROM Village v")
    Optional<Integer> findMaxY();

    boolean existsByCoordinates(Coordinates coordinates);

}
