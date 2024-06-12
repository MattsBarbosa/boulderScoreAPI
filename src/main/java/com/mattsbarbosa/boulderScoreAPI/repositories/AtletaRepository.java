package com.mattsbarbosa.boulderScoreAPI.repositories;

import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {
}
