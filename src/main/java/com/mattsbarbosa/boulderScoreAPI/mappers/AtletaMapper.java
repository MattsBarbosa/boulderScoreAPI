package com.mattsbarbosa.boulderScoreAPI.mappers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;

public class AtletaMapper {

    public static AtletaDto mapParaAtletaDto(Atleta atleta){
        return new AtletaDto(
                atleta.getId(),
                atleta.getNome(),
                atleta.getNumero(),
                atleta.getPontuacao(),
                atleta.getCategoria(),
                atleta.getBoulders()
        );
    }

    public static Atleta mapParaAtleta(AtletaDto atletaDto){
        return new Atleta(
                atletaDto.getId(),
                atletaDto.getNome(),
                atletaDto.getNumero(),
                atletaDto.getPontuacao(),
                atletaDto.getCategoria(),
                atletaDto.getBoulders()
        );
    }
}
