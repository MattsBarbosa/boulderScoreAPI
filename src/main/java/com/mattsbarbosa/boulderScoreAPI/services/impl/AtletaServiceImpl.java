package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.AtletaMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.repositories.BoulderRepository;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtletaServiceImpl implements AtletaService {

    private final AtletaRepository atletaRepository;
    private final BoulderRepository boulderRepository;

    @Override
    @Transactional
    public AtletaDto criarAtleta(AtletaDto atletaDto) {

        Atleta atleta = new Atleta();
        atleta.setNome(atletaDto.nome());
        atleta.setNumero(atletaDto.numero());
        atleta.setCategoria(atletaDto.categoria());
        atleta.setPontuacaoTotal(atletaDto.pontuacaoTotal());
        atleta.setBoulders(boulderRepository.findAll().stream().collect(Collectors.toSet()));

        Atleta atletaSalvo = atletaRepository.save(atleta);
        return AtletaMapper.mapParaAtletaDto(atletaSalvo);
    }

    @Override
    public AtletaDto pegarAtletaPorId(UUID atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        return AtletaMapper.mapParaAtletaDto(atleta);
    }

    @Override
    public Set<AtletaDto> pegarTodosAtletas() {
        List<Atleta> atletas = atletaRepository.findAll();
        return atletas.stream().map(AtletaMapper::mapParaAtletaDto)
                .collect(Collectors.toSet());
    }

    @Override
    public AtletaDto atualizarAtleta(UUID atletaId, AtletaDto atletaAtualizado) {
        Atleta atleta = atletaRepository.findById(atletaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        atleta.setNome(atletaAtualizado.nome());
        atleta.setNumero(atletaAtualizado.numero());
        atleta.setCategoria(atletaAtualizado.categoria());
        atleta.setPontuacaoTotal(atletaAtualizado.pontuacaoTotal());

        var atletaAtualizadoSalvo = atletaRepository.save(atleta);

        return AtletaMapper.mapParaAtletaDto(atletaAtualizadoSalvo);
    }

    @Override
    public void deletaAtleta(UUID atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId).orElseThrow(
                () -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        atletaRepository.deleteById(atleta.getId());
    }
}
