package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;

import java.util.List;
import java.util.UUID;

public interface IAtletaService {

    AtletaDTO saveAtleta(AtletaDTO atletaDTO);

    AtletaDTO getAtletaById(UUID atletaId);

    List<AtletaDTO> getAllAtletas();

    AtletaDTO updateAtleta(UUID atletaId, AtletaDTO atletaAtualizado);

    void deleteAtleta(UUID atletaId);

}
