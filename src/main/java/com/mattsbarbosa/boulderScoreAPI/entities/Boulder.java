package com.mattsbarbosa.boulderScoreAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boulder")
public class Boulder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Long numero;

    @ElementCollection
    @Column(name = "pontuacao")
    private Map<Number, Number> pontuacao;

    @Column(name = "tentativas")
    private Number tentativas;


}