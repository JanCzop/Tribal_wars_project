package com.example.tribal_wars.backend.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Player_repository extends JpaRepository<Player,Long> {
}
