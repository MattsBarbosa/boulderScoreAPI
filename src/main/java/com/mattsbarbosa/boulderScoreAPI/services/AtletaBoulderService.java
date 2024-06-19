package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;

import java.util.List;
import java.util.UUID;

public interface AtletaBoulderService {

    List<AtletaBoulderDTO> getAllBouldersFromAtleta(UUID atletaId);

    void recordAttempt(UUID atletaBoulderId);

    void recordSend(UUID atletaBoulderId);

    Double calculateScore(Boulder boulder, Integer tries);

}
