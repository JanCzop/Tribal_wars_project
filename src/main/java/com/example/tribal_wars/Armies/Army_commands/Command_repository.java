package com.example.tribal_wars.Armies.Army_commands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Command_repository extends JpaRepository<Command, Long> {
}
