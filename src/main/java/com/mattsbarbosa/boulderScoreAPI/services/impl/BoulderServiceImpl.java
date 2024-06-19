package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.CompetitionMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaBoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.BoulderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoulderServiceImpl implements BoulderService {

    private final BoulderRepository boulderRepository;
    private final CompetitionMapper competitionMapper;
    private final AtletaRepository atletaRepository;
    private final AtletaBoulderRepository atletaBoulderRepository;

    @Override
    @Transactional
    public BoulderDTO saveBoulder(BoulderDTO boulderDTO) {

        Boulder boulder = new Boulder();
        boulder.setNumero(boulderDTO.getNumero());
        boulder.setPontuacaoPrimeiraTentativa(boulderDTO.getPontuacaoPrimeiraTentativa());
        boulder.setPontuacaoSegundaTentativa(boulderDTO.getPontuacaoSegundaTentativa());
        boulder.setPontuacaoPadrao(boulderDTO.getPontuacaoPadrao());

        Boulder boulderSalvo = boulderRepository.save(boulder);
        return competitionMapper.toBoulderDTO(boulderSalvo);
    }

    @Override
    public BoulderDTO getBoulderById(UUID boulderId) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));
        return competitionMapper.toBoulderDTO(boulder);
    }

    @Override
    public List<BoulderDTO> getAllBoulders() {
        return boulderRepository.findAll()
                        .stream()
                        .map((competitionMapper::toBoulderDTO))
                        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void assignBouldersToAllAtletas(List<UUID> bouldersDtoId) {
        List<Atleta> atletas = atletaRepository.findAll();
        List<Boulder> boulders = boulderRepository.findAllById(bouldersDtoId);

        List<AtletaBoulder> atletaBoulders = atletas.stream()
                .flatMap(atleta -> boulders.stream()
                        .map(boulder -> {
                            AtletaBoulder atletaBoulder = new AtletaBoulder();
                            atletaBoulder.setAtleta(atleta);
                            atletaBoulder.setBoulder(boulder);
                            atletaBoulder.setBoulderNumero(boulder.getNumero());
                            atletaBoulder.setTentativas(0);
                            atletaBoulder.setPontuacao(0.0);
                            atletaBoulder.setEncadenado(false);
                            return atletaBoulder;
                        }))
                .toList();

        atletaBoulderRepository.saveAll(atletaBoulders);
    }

    @Override
    @Transactional
    public BoulderDTO updateBoulder(UUID boulderId, BoulderDTO boulderAtualizado) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));

        boulder.setNumero(boulderAtualizado.getNumero());
        boulder.setPontuacaoPrimeiraTentativa(boulderAtualizado.getPontuacaoPrimeiraTentativa());
        boulder.setPontuacaoSegundaTentativa(boulderAtualizado.getPontuacaoSegundaTentativa());
        boulder.setPontuacaoPadrao(boulderAtualizado.getPontuacaoPadrao());

        var boulderAtualizadoSalvo = boulderRepository.save(boulder);

        return competitionMapper.toBoulderDTO(boulderAtualizadoSalvo);
    }

    @Override
    @Transactional
    public void deleteBoulder(UUID boulderId) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));

        boulderRepository.deleteById(boulder.getId());
    }
}
