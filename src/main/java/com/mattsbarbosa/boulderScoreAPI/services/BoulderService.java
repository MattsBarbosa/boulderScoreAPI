package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;

import java.util.List;

public interface BoulderService {

    BoulderDto criarBoulder(BoulderDto boulderDto);

    BoulderDto pegarBoulderPorId(Long boulderId);

    List<BoulderDto> pegarTodosBoulders();

    BoulderDto atualizarBoulder(Long boulderId, BoulderDto boulderAtualizado);

    void deletaBoulder(Long boulderId);
}
