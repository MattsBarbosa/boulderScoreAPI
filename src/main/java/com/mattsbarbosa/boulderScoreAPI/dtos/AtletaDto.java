package com.mattsbarbosa.boulderScoreAPI.dtos;

import java.util.Set;
import java.util.UUID;

public record AtletaDto(UUID id,
                        String nome,
                        Integer numero,
                        Double pontuacaoTotal,
                        String categoria,
                        Set<UUID> bouldersIds){

}