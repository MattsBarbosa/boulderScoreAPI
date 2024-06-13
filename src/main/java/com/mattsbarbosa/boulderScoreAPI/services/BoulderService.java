package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;

import java.util.Set;
import java.util.UUID;

public interface BoulderService {

    BoulderDto criarBoulder(BoulderDto boulderDto);

    BoulderDto pegarBoulderPorId(UUID boulderId);

    Set<BoulderDto> pegarTodosBoulders();

    BoulderDto atualizarBoulder(UUID boulderId, BoulderDto boulderAtualizado);

    void deletaBoulder(UUID boulderId);
}
