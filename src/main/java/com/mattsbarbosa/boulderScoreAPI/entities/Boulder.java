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
@Table(name = "BOULDERS")
public class Boulder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Double pontuacaoPrimeiraTentativa;

    @Column(nullable = false)
    private Double pontuacaoSegundaTentativa;

    @Column(nullable = false)
    private Double pontuacaoPadrao;

    @OneToMany(mappedBy = "boulder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtletaBoulder> atletaBoulders = new ArrayList<>();

}