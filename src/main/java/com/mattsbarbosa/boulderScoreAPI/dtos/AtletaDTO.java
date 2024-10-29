package com.mattsbarbosa.boulderScoreAPI.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class AtletaDTO {

    private UUID id;

    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Min(value = 1, message = "O número deve ser maior que zero")
    private Integer numero;

    @PositiveOrZero(message = "A pontuação deve ser um valor positivo")
    private Double pontuacaoTotal;

    @NotBlank(message = "A categoria não pode ser vazia")
    private String categoria;

}