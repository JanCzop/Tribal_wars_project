package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Player_repository extends JpaRepository<Player,Long> {
    Optional<Player> findByUsername(String username);
}
