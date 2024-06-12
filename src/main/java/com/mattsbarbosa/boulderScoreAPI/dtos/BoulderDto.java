package com.mattsbarbosa.boulderScoreAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoulderDto {

    private Long id;
    private Long numero;
    private Map<Number, Number> pontuacao;
    private Number tentativas;
}
