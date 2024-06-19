package com.mattsbarbosa.boulderScoreAPI.mappers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    //DTO to entity
    AtletaDTO toAtletaDTO(Atleta atleta);

    BoulderDTO toBoulderDTO(Boulder boulder);

    @Mapping(target = "atletaId", source = "atleta.id")
    @Mapping(target = "boulderId", source = "boulder.id")
    @Mapping(target = "boulderNumero", source = "boulderNumero")
    AtletaBoulderDTO toAtletaBoulderDTO(AtletaBoulder atletaBoulder);

    //Entity to DTO
    Atleta toAtletaEntity(AtletaDTO atletaDTO);

    Boulder toBoulderEntity(BoulderDTO boulderDTO);

    @Mapping(target = "atleta.id", source = "atletaId")
    @Mapping(target = "boulder.id", source = "boulderId")
    @Mapping(target = "boulderNumero", source = "boulderNumero")
    AtletaBoulder toAtletaBoulderEntity(AtletaBoulderDTO atletaBoulderDTO);
}
