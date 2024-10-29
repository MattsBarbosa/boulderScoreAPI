package com.mattsbarbosa.boulderScoreAPI.mappers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.UserDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface MyMapper {

    @Autowired
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    //Entity to DTO
    AtletaDTO toAtletaDTO(Atleta atleta);
    BoulderDTO toBoulderDTO(Boulder boulder);
    UserDTO toUserDTO(User user);

    @Mapping(target = "atletaId", source = "atleta.id")
    @Mapping(target = "boulderId", source = "boulder.id")
    AtletaBoulderDTO toAtletaBoulderDTO(AtletaBoulder atletaBoulder);

    //DTO to Entity
    Atleta toAtletaEntity(AtletaDTO atletaDTO);
    Boulder toBoulderEntity(BoulderDTO boulderDTO);

    @Mapping(target = "password", expression = "java(encoder.encode(userDTO.getPassword()))")
    User toUserEntity(UserDTO userDTO);

    @Mapping(target = "atleta.id", source = "atletaId")
    @Mapping(target = "boulder.id", source = "boulderId")
    AtletaBoulder toAtletaBoulderEntity(AtletaBoulderDTO atletaBoulderDTO);

    //To Update DTO to Entity
    void updateAtletaFromDto(AtletaDTO atletaDTO, @MappingTarget Atleta atleta);
    void updateBoulderFromDto(BoulderDTO boulderDTO, @MappingTarget Boulder boulder);

}
