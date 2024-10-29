package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.exception.CustomResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.services.BaseService;
import com.mattsbarbosa.boulderScoreAPI.services.IAtletaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtletaServiceImpl extends BaseService<AtletaDTO> implements IAtletaService {

    private final AtletaRepository atletaRepository;

    @Override
    @Transactional
    public AtletaDTO saveAtleta(AtletaDTO atletaDTO) {
        validateDto(atletaDTO);
        return myMapper.toAtletaDTO(atletaRepository.save(myMapper.toAtletaEntity(atletaDTO)));
    }

    @Override
    public AtletaDTO getAtletaById(UUID atletaId) {
        return atletaRepository.findById(atletaId)
                .map(myMapper::toAtletaDTO)
                .orElseThrow(() -> new CustomResourceNotFoundException(
                        "Atleta não encontrado com o id: " + atletaId));
    }

    @Override
    public List<AtletaDTO> getAllAtletas() {
        return atletaRepository.findAll()
                .stream()
                .map(myMapper::toAtletaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AtletaDTO updateAtleta(UUID atletaId, AtletaDTO atletaAtualizado) {
        validateDto(atletaAtualizado);
        return atletaRepository.findById(atletaId)
                .map(atleta -> {
                    myMapper.updateAtletaFromDto(atletaAtualizado, atleta);
                    return myMapper.toAtletaDTO(atletaRepository.save(atleta));
                })
                .orElseThrow(() -> new CustomResourceNotFoundException(
                        "Atleta não encontrado com o id: " + atletaId));
    }

    @Override
    @Transactional
    public void deleteAtleta(UUID atletaId) {
        atletaRepository.deleteById(atletaId);
    }

}
