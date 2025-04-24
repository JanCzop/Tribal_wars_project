package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Player_repository extends JpaRepository<Player,Long> {
    Optional<Player> findByUsername(String username);
    @Query("SELECT p FROM Player p LEFT JOIN FETCH p.authorities WHERE p.username = :username")
    Optional<Player> findByUsernameEAGER(@Param("username") String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
