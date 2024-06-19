package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.CompetitionMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtletaServiceImpl implements AtletaService {

    private final AtletaRepository atletaRepository;
    private final CompetitionMapper competitionMapper;

    @Override
    @Transactional
    public AtletaDTO saveAtleta(AtletaDTO atletaDTO) {

        Atleta atleta = new Atleta();
        atleta.setNome(atletaDTO.getNome());
        atleta.setNumero(atletaDTO.getNumero());
        atleta.setCategoria(atletaDTO.getCategoria());
        atleta.setPontuacaoTotal(atletaDTO.getPontuacaoTotal());

        Atleta atletaSalvo = atletaRepository.save(atleta);
        return competitionMapper.toAtletaDTO(atletaSalvo);
    }

    @Override
    public AtletaDTO getAtletaById(UUID atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        return competitionMapper.toAtletaDTO(atleta);
    }

    @Override
    public List<AtletaDTO> getAllAtletas() {
        return atletaRepository.findAll()
                .stream()
                .map(competitionMapper::toAtletaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AtletaDTO updateAtleta(UUID atletaId, AtletaDTO atletaAtualizado) {
        Atleta atleta = atletaRepository.findById(atletaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        atleta.setNome(atletaAtualizado.getNome());
        atleta.setNumero(atletaAtualizado.getNumero());
        atleta.setCategoria(atletaAtualizado.getCategoria());
        atleta.setPontuacaoTotal(atletaAtualizado.getPontuacaoTotal());

        var atletaAtualizadoSalvo = atletaRepository.save(atleta);

        return competitionMapper.toAtletaDTO(atletaAtualizadoSalvo);
    }

    @Override
    @Transactional
    public void deleteAtleta(UUID atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId).orElseThrow(
                () -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        atletaRepository.deleteById(atleta.getId());
    }
}
