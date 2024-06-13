package com.mattsbarbosa.boulderScoreAPI.mappers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor
public class AtletaMapper {

    public static AtletaDto mapParaAtletaDto(Atleta atleta){
        return new AtletaDto(
                atleta.getId(),
                atleta.getNome(),
                atleta.getNumero(),
                atleta.getPontuacaoTotal(),
                atleta.getCategoria(),
                atleta.getBoulders().stream().map(Boulder::getId).collect(Collectors.toSet())
        );
    }
}
