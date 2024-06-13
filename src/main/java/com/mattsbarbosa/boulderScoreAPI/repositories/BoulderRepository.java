package com.mattsbarbosa.boulderScoreAPI.repositories;

import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoulderRepository extends JpaRepository<Boulder, UUID> {
}
