package com.mattsbarbosa.boulderScoreAPI.mappers;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;

import java.util.stream.Collectors;

public class BoulderMapper {

    public static BoulderDto mapParaBoulderDto(Boulder boulder){
        return new BoulderDto(
                boulder.getId(),
                boulder.getNumero(),
                boulder.getAtletas().stream().map(Atleta::getId).collect(Collectors.toSet()),
                boulder.getTentativas(),
                boulder.getPontuacaoPrimeiraTentativa(),
                boulder.getPontuacaoSegundaTentativa(),
                boulder.getPontuacaoPadrao()
        );
    }
}
