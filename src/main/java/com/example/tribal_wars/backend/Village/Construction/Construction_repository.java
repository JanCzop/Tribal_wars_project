package com.example.tribal_wars.backend.Village.Construction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Construction_repository extends JpaRepository<Construction,Long> {
}
