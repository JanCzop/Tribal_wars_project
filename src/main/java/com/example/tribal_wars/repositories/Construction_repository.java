package com.example.tribal_wars.repositories;

import com.example.tribal_wars.entities.village.embbed.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Construction_repository extends JpaRepository<Construction,Long> {
}
