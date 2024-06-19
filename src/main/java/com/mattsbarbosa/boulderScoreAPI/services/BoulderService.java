package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;

import java.util.List;
import java.util.UUID;

public interface BoulderService {

    BoulderDTO saveBoulder(BoulderDTO boulderDTO);

    BoulderDTO getBoulderById(UUID boulderId);

    List<BoulderDTO> getAllBoulders();

    void assignBouldersToAllAtletas(List<UUID> bouldersDtoId);

    BoulderDTO updateBoulder(UUID boulderId, BoulderDTO boulderAtualizado);

    void deleteBoulder(UUID boulderId);

}
