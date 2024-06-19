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
public class AtletaBoulderDTO {
    private UUID id;
    private UUID atletaId;
    private UUID boulderId;
    private Integer boulderNumero;
    private Integer tentativas;
    private Double pontuacao;
    private Boolean encadenado;
}
