package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.CompetitionMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaBoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaBoulderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtletaBoulderServiceImpl implements AtletaBoulderService {

    private final AtletaBoulderRepository atletaBoulderRepository;
    private final AtletaRepository atletaRepository;
    private final BoulderRepository boulderRepository;
    private final CompetitionMapper competitionMapper;

    @Override
    public List<AtletaBoulderDTO> getAllBouldersFromAtleta(UUID atletaId) {
        return atletaBoulderRepository.findByAtletaId(atletaId).stream()
                .map(competitionMapper::toAtletaBoulderDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void recordAttempt(UUID atletaBoulderId) {
        AtletaBoulder atletaBoulder = atletaBoulderRepository.findById(atletaBoulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Relação Atleta Boulder não encontrada"));

        atletaBoulder.setTentativas(atletaBoulder.getTentativas() + 1);
        atletaBoulderRepository.save(atletaBoulder);
    }

    @Override
    @Transactional
    public void recordSend(UUID atletaBoulderId) {
        AtletaBoulder atletaBoulder = atletaBoulderRepository.findById(atletaBoulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Relação Atleta Boulder não encontrada"));

        Atleta atleta = atletaRepository.findById(atletaBoulder.getAtleta().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado"));

        Boulder boulder = boulderRepository.findById(atletaBoulder.getBoulder().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado"));

        Double score = calculateScore(boulder, atletaBoulder.getTentativas());

        atletaBoulder.setEncadenado(true);
        atletaBoulder.setPontuacao(score);
        atletaBoulderRepository.save(atletaBoulder);

        atleta.setPontuacaoTotal(atleta.getPontuacaoTotal() + atletaBoulder.getPontuacao());
        atletaRepository.save(atleta);
    }

    @Override
    public Double calculateScore(Boulder boulder, Integer tries) {
        if (tries == 1) {
            return boulder.getPontuacaoPrimeiraTentativa();
        }else if (tries == 2) {
            return boulder.getPontuacaoSegundaTentativa();
        }else {
            return boulder.getPontuacaoPadrao();
        }
    }
}
