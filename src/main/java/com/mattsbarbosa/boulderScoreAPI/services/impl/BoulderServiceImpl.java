package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.BoulderMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.BoulderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoulderServiceImpl implements BoulderService {

    BoulderRepository boulderRepository;

    @Override
    public BoulderDto criarBoulder(BoulderDto boulderDto) {
        Boulder boulder = BoulderMapper.mapParaBoulder(boulderDto);
        Boulder boulderSalvo = boulderRepository.save(boulder);
        return BoulderMapper.mapParaBoulderDto(boulderSalvo);
    }

    @Override
    public BoulderDto pegarBoulderPorId(Long boulderId) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));
        return BoulderMapper.mapParaBoulderDto(boulder);
    }

    @Override
    public List<BoulderDto> pegarTodosBoulders() {
        List<Boulder> boulders = boulderRepository.findAll();
        return boulders.stream().map((BoulderMapper::mapParaBoulderDto))
                .collect(Collectors.toList());
    }

    @Override
    public BoulderDto atualizarBoulder(Long boulderId, BoulderDto boulderAtualizado) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));

        boulder.setNumero(boulderAtualizado.getNumero());
        boulder.setPontuacao(boulderAtualizado.getPontuacao());
        boulder.setTentativas(boulderAtualizado.getTentativas());

        var objetoBoulderAtualizado = boulderRepository.save(boulder);

        return BoulderMapper.mapParaBoulderDto(objetoBoulderAtualizado);
    }

    @Override
    public void deletaBoulder(Long boulderId) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));

        boulderRepository.deleteById(boulder.getId());
    }
}
