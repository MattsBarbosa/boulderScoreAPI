package com.mattsbarbosa.boulderScoreAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ATLETAS")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "pontuacao_total", nullable = false)
    private Double pontuacaoTotal = 0.0;

    @Column(name = "categoria")
    private String categoria;

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtletaBoulder> atletaBoulders = new ArrayList<>();

}
