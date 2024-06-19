package com.mattsbarbosa.boulderScoreAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoulderDTO {

    private UUID id;
    private Integer numero;
    private Double pontuacaoPrimeiraTentativa;
    private Double pontuacaoSegundaTentativa;
    private Double pontuacaoPadrao;
}