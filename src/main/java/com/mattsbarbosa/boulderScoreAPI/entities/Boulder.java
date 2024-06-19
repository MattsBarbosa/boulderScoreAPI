package com.mattsbarbosa.boulderScoreAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_BOULDERS")
public class Boulder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "primeira_tentativa")
    private Double pontuacaoPrimeiraTentativa;

    @Column(name = "segunda_tentativa")
    private Double pontuacaoSegundaTentativa;

    @Column(name = "pontuacao_padrao")
    private Double pontuacaoPadrao;

    @OneToMany(mappedBy = "boulder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtletaBoulder> atletaBoulders = new ArrayList<>();

}