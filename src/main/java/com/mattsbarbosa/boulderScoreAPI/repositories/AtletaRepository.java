package com.mattsbarbosa.boulderScoreAPI.repositories;

import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AtletaRepository extends JpaRepository<Atleta, UUID> {

    //testar
    Atleta findAtletaByCategoria(String categoria);
}
