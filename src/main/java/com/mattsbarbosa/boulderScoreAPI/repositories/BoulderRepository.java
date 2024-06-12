package com.mattsbarbosa.boulderScoreAPI.repositories;

import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoulderRepository extends JpaRepository<Boulder, Long> {
}
