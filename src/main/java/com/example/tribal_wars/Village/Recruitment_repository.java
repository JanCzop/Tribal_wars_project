package com.example.tribal_wars.Village;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Recruitment_repository extends JpaRepository<Recruitment,Long> {
}
