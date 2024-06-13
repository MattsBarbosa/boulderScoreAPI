package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;

import java.util.Set;
import java.util.UUID;

public interface AtletaService {

    AtletaDto criarAtleta(AtletaDto atletaDto);

    AtletaDto pegarAtletaPorId(UUID atletaId);

    Set<AtletaDto> pegarTodosAtletas();

    AtletaDto atualizarAtleta(UUID atletaId, AtletaDto atletaAtualizado);

    void deletaAtleta(UUID atletaId);

}
