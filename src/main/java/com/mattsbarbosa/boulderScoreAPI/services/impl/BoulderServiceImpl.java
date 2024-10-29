package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.entities.AtletaBoulder;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.exception.CustomResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaBoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.BaseService;
import com.mattsbarbosa.boulderScoreAPI.services.IBoulderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoulderServiceImpl extends BaseService<BoulderDTO> implements IBoulderService {

    private final BoulderRepository boulderRepository;
    private final AtletaRepository atletaRepository;
    private final AtletaBoulderRepository atletaBoulderRepository;

    @Override
    @Transactional
    public BoulderDTO saveBoulder(BoulderDTO boulderDTO) {
        validateDto(boulderDTO);
        return myMapper.toBoulderDTO(boulderRepository.save(myMapper.toBoulderEntity(boulderDTO)));
    }

    @Override
    public BoulderDTO getBoulderById(UUID boulderId) {
        return boulderRepository.findById(boulderId)
                .map(myMapper::toBoulderDTO)
                .orElseThrow(() ->new CustomResourceNotFoundException(
                        "Boulder não encontrado com o id: " + boulderId));
    }

    @Override
    public List<BoulderDTO> getAllBoulders() {
        return boulderRepository.findAll()
                        .stream()
                        .map((myMapper::toBoulderDTO))
                        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void assignBouldersToAllAtletas(List<UUID> bouldersDtoId) {

        List<Atleta> atletas;
        List<Boulder> boulders;

        atletas = atletaRepository.findAll();
        boulders = boulderRepository.findAllById(bouldersDtoId);

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
        validateDto(boulderAtualizado);

        return boulderRepository.findById(boulderId)
                .map(boulder -> {
                    myMapper.updateBoulderFromDto(boulderAtualizado, boulder);
                    return myMapper.toBoulderDTO(boulder);
                })
                .orElseThrow(() -> new CustomResourceNotFoundException(
                        "Boulder não encontrado com o id : " + boulderId));
    }

    @Override
    @Transactional
    public void deleteBoulder(UUID boulderId) {
        boulderRepository.deleteById(boulderId);
    }
}
