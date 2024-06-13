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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "boulders", fetch = FetchType.LAZY) //evita carregamento desnecessário das coleções
    private Set<Atleta> atletas = new HashSet<>();

    @Column(name = "tentativas")
    private Integer tentativas = 0;

    @Column(name = "primeira_tentativa")
    private Double pontuacaoPrimeiraTentativa;

    @Column(name = "segunda_tentativa")
    private Double pontuacaoSegundaTentativa;

    @Column(name = "pontuacao_padrao")
    private Double pontuacaoPadrao;


}