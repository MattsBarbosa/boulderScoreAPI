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
@Table(name = "ATLETAS")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Double pontuacaoTotal = 0.0;

    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtletaBoulder> atletaBoulders = new ArrayList<>();

}
