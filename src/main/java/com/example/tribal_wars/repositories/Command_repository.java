package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.army.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Command_repository extends JpaRepository<Command, Long> {
}
