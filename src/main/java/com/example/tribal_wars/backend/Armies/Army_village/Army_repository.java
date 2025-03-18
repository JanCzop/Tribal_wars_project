package com.example.tribal_wars.backend.Armies.Army_village;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Army_repository extends JpaRepository<Army,Long> {
}
