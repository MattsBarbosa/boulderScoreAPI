package com.mattsbarbosa.boulderScoreAPI.dtos;

import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Categorias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtletaDto {

    private Long id;
    private String nome;
    private Number numero;
    private BigDecimal pontuacao;
    private Categorias categoria;
    private List<Boulder> boulders;
}
