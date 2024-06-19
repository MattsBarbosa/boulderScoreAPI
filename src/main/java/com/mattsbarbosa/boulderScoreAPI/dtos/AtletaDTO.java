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
public class AtletaDTO {

    private UUID id;
    private String nome;
    private Integer numero;
    private Double pontuacaoTotal;
    private String categoria;

}