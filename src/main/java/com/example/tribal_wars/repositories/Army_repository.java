package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.army.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Army_repository extends JpaRepository<Army,Long> {
}
