package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Boulder;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.BoulderMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.BoulderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoulderServiceImpl implements BoulderService {

    private final BoulderRepository boulderRepository;
    private final AtletaRepository atletaRepository;

    @Override
    @Transactional
    public BoulderDto criarBoulder(BoulderDto boulderDto) {

        Boulder boulder = new Boulder();
        boulder.setNumero(boulderDto.numero());
        boulder.setTentativas(boulderDto.tentativas());
        boulder.setPontuacaoPrimeiraTentativa(boulderDto.pontuacaoPrimeiraTentativa());
        boulder.setPontuacaoSegundaTentativa(boulderDto.pontuacaoSegundaTentativa());
        boulder.setPontuacaoPadrao(boulderDto.pontuacaoPadrao());
        boulder.setAtletas(atletaRepository.findAllById(boulderDto.atletasIds()).stream().collect(Collectors.toSet()));

        Boulder boulderSalvo = boulderRepository.save(boulder);
        return BoulderMapper.mapParaBoulderDto(boulderSalvo);
    }

    @Override
    public BoulderDto pegarBoulderPorId(UUID boulderId) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));
        return BoulderMapper.mapParaBoulderDto(boulder);
    }

    @Override
    public Set<BoulderDto> pegarTodosBoulders() {
        List<Boulder> boulders = boulderRepository.findAll();
        return boulders.stream().map((BoulderMapper::mapParaBoulderDto))
                .collect(Collectors.toSet());
    }

    @Override
    public BoulderDto atualizarBoulder(UUID boulderId, BoulderDto boulderAtualizado) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));

        boulder.setNumero(boulderAtualizado.numero());
        boulder.setTentativas(boulderAtualizado.tentativas());
        boulder.setPontuacaoPrimeiraTentativa(boulderAtualizado.pontuacaoPrimeiraTentativa());
        boulder.setPontuacaoSegundaTentativa(boulderAtualizado.pontuacaoSegundaTentativa());
        boulder.setPontuacaoPadrao(boulderAtualizado.pontuacaoPadrao());

        var boulderAtualizadoSalvo = boulderRepository.save(boulder);

        return BoulderMapper.mapParaBoulderDto(boulderAtualizadoSalvo);
    }

    @Override
    public void deletaBoulder(UUID boulderId) {
        Boulder boulder = boulderRepository.findById(boulderId)
                .orElseThrow(() -> new ResourceNotFoundException("Boulder não encontrado com o id : " + boulderId));

        boulderRepository.deleteById(boulder.getId());
    }
}
