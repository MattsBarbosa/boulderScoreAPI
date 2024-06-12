package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;

import java.util.List;

public interface AtletaService {

    AtletaDto criarAtleta(AtletaDto atletaDto);

    AtletaDto pegarAtletaPorId(Long atletaId);

    List<AtletaDto> pegarTodosAtletas();

    AtletaDto atualizarAtleta(Long atletaId, AtletaDto atletaAtualizado);

    void deletaAtleta(Long atletaId);

}
