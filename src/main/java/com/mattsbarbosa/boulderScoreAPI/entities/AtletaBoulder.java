package com.mattsbarbosa.boulderScoreAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atleta_boulder")
public class AtletaBoulder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "atleta_id")
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "boulder_id")
    private Boulder boulder;

    @Column(name= "boulder_numero", nullable = false)
    private Integer boulderNumero;

    @Column(name = "tentativas", nullable = false)
    private Integer tentativas = 0;

    @Column(name = "pontuacao")
    private Double pontuacao;

    @Column(name = "encadenado", nullable = false)
    private Boolean encadenado = false;

}