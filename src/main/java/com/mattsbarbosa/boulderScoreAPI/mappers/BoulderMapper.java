package com.mattsbarbosa.boulderScoreAPI.mappers;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;

public class BoulderMapper {

    public static BoulderDto mapParaBoulderDto(Boulder boulder){
        return new BoulderDto(
                boulder.getId(),
                boulder.getNumero(),
                boulder.getPontuacao(),
                boulder.getTentativas()
        );
    }

    public static Boulder mapParaBoulder(BoulderDto boulderDto){
        return new Boulder(
                boulderDto.getId(),
                boulderDto.getNumero(),
                boulderDto.getPontuacao(),
                boulderDto.getTentativas()
        );
    }
}
