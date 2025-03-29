package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.entities.village.embbed.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Village_repository extends JpaRepository<Village, Coordinates> {
}
