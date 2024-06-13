package com.mattsbarbosa.boulderScoreAPI.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "pontuacao_total")
    private Double pontuacaoTotal = 0.0;

    @Column(name = "categoria")
    private String categoria;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_boulder_atleta",
            joinColumns = @JoinColumn(name = "boulder_id"),
            inverseJoinColumns = @JoinColumn(name = "atleta_id"))
    private Set<Boulder> boulders = new HashSet<>();



}
