package com.mattsbarbosa.boulderScoreAPI.repositories;

import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AtletaBoulderRepository extends JpaRepository<AtletaBoulder, UUID> {

    List<AtletaBoulder> findByAtletaId(UUID atletaId);
}
