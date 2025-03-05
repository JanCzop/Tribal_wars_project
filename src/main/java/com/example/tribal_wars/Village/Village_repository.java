package com.example.tribal_wars.Village;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Village_repository extends JpaRepository<Village, Long> {
}
