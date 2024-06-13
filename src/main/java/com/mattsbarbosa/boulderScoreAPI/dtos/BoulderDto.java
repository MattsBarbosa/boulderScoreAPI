package com.mattsbarbosa.boulderScoreAPI.dtos;

import java.util.Set;
import java.util.UUID;

public record BoulderDto(UUID id,
                         Integer numero,
                         Set<UUID> atletasIds,
                         Integer tentativas,
                         Double pontuacaoPrimeiraTentativa,
                         Double pontuacaoSegundaTentativa,
                         Double pontuacaoPadrao){
}