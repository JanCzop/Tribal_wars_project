package com.example.tribal_wars.Armies.Army_village;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Army_repository extends JpaRepository<Army,Army.Army_id> {
}
