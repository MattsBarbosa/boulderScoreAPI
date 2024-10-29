package com.mattsbarbosa.boulderScoreAPI.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @Min(value = 1, message = "O número deve ser maior que zero")
    private Integer boulderNumero;

    @Positive(message = "A tentativa deve ser um valor positivo")
    private Integer tentativas;

    @PositiveOrZero(message = "A pontuação deve ser um valor positivo")
    private Double pontuacao;

    private Boolean encadenado;
}
