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
@Table(name = "ATLETA_BOULDER")
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

    @Column(nullable = false)
    private Integer boulderNumero;

    @Column(nullable = false)
    private Integer tentativas = 0;

    @Column(nullable = false)
    private Double pontuacao;

    @Column(nullable = false)
    private Boolean encadenado = false;

}