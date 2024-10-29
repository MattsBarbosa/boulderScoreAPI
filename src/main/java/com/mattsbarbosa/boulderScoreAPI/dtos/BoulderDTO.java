package com.mattsbarbosa.boulderScoreAPI.dtos;

import jakarta.validation.constraints.Min;
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

    @Min(value = 1, message = "Número deve ser maior que zero")
    private Integer numero;

    @Min(value = 1, message = "Pontuação deve ser maior que zero")
    private Double pontuacaoPrimeiraTentativa;

    @Min(value = 1, message = "Pontuação deve ser maior que zero")
    private Double pontuacaoSegundaTentativa;

    @Min(value = 1, message = "Pontuação deve ser maior que zero")
    private Double pontuacaoPadrao;
}