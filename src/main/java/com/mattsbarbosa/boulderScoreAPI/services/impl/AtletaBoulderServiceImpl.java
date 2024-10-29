package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.exception.CustomResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.MyMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaBoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.IAtletaBoulderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtletaBoulderServiceImpl implements IAtletaBoulderService {

    private final AtletaBoulderRepository atletaBoulderRepository;
    private final AtletaRepository atletaRepository;
    private final BoulderRepository boulderRepository;
    private final MyMapper myMapper;

    @Override
    public List<AtletaBoulderDTO> getAllBouldersFromAtleta(UUID atletaId) {
        return atletaBoulderRepository.findByAtletaId(atletaId).stream()
                .map(myMapper::toAtletaBoulderDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void recordAttempt(UUID atletaBoulderId) {
        atletaBoulderRepository.findById(atletaBoulderId)
                .map(atletaBoulder -> {
                    atletaBoulder.setTentativas(atletaBoulder.getTentativas() + 1);
                    return atletaBoulderRepository.save(atletaBoulder);
                })
                .orElseThrow(() -> new CustomResourceNotFoundException(
                        "Relação Atleta Boulder não encontrada"));
    }

    @Override
    public Double calculateScore(Boulder boulder, Integer tries) {

        if (tries < 1) {
            throw new IllegalArgumentException("O número de tentativas deve ser maior que 0");
        }

        return switch (tries) {
            case 1 -> boulder.getPontuacaoPrimeiraTentativa();
            case 2 -> boulder.getPontuacaoSegundaTentativa();
            default -> boulder.getPontuacaoPadrao();
        };
    }

    @Override
    @Transactional
    public void recordSend(UUID atletaBoulderId) {
        AtletaBoulder atletaBoulder = findAtletaBoulderById(atletaBoulderId);
        Atleta atleta = findAtletaById(atletaBoulder.getAtleta().getId());
        Boulder boulder = findBoulderById(atletaBoulder.getBoulder().getId());

        updateAtletaBoulder(atletaBoulder, boulder);
        updateAtleta(atleta, atletaBoulder.getPontuacao());
    }

    //Methods to use in recordSend

    private AtletaBoulder findAtletaBoulderById(UUID atletaBoulderId) {
        return atletaBoulderRepository.findById(atletaBoulderId)
                .orElseThrow(() -> new CustomResourceNotFoundException(
                        "Relação Atleta Boulder não encontrada"));
    }

    private Atleta findAtletaById(UUID atletaId) {
        return atletaRepository.findById(atletaId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Atleta não encontrado"));
    }

    private Boulder findBoulderById(UUID boulderId) {
        return boulderRepository.findById(boulderId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Boulder não encontrado"));
    }

    private void updateAtletaBoulder(AtletaBoulder atletaBoulder, Boulder boulder) {
        atletaBoulder.setTentativas(atletaBoulder.getTentativas() + 1);
        Double score = calculateScore(boulder, atletaBoulder.getTentativas());
        atletaBoulder.setEncadenado(true);
        atletaBoulder.setPontuacao(score);
        atletaBoulderRepository.save(atletaBoulder);
    }

    private void updateAtleta(Atleta atleta, Double score) {
        atleta.setPontuacaoTotal(atleta.getPontuacaoTotal() + score);
        atletaRepository.save(atleta);
    }
}
