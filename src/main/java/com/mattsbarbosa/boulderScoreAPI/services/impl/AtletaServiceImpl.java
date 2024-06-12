package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;
import com.mattsbarbosa.boulderScoreAPI.entities.Atleta;
import com.mattsbarbosa.boulderScoreAPI.exception.ResourceNotFoundException;
import com.mattsbarbosa.boulderScoreAPI.mappers.AtletaMapper;
import com.mattsbarbosa.boulderScoreAPI.repositories.AtletaRepository;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtletaServiceImpl implements AtletaService {

    private AtletaRepository atletaRepository;

    @Override
    public AtletaDto criarAtleta(AtletaDto atletaDto) {
        Atleta atleta = AtletaMapper.mapParaAtleta(atletaDto);
        Atleta atletaSalvo = atletaRepository.save(atleta);
        return AtletaMapper.mapParaAtletaDto(atletaSalvo);
    }

    @Override
    public AtletaDto pegarAtletaPorId(Long atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        return AtletaMapper.mapParaAtletaDto(atleta);
    }

    @Override
    public List<AtletaDto> pegarTodosAtletas() {
        List<Atleta> atletas = atletaRepository.findAll();
        return atletas.stream().map(AtletaMapper::mapParaAtletaDto)
                .collect(Collectors.toList());
    }

    @Override
    public AtletaDto atualizarAtleta(Long atletaId, AtletaDto atletaAtualizado) {
        Atleta atleta = atletaRepository.findById(atletaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        atleta.setNome(atletaAtualizado.getNome());
        atleta.setNumero(atletaAtualizado.getNumero());
        atleta.setCategoria(atletaAtualizado.getCategoria());
        atleta.setPontuacao(atletaAtualizado.getPontuacao());
        atleta.setBoulders(atletaAtualizado.getBoulders());

        var objetoAtletaAtualizado = atletaRepository.save(atleta);

        return AtletaMapper.mapParaAtletaDto(objetoAtletaAtualizado);
    }

    @Override
    public void deletaAtleta(Long atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId).orElseThrow(
                () -> new ResourceNotFoundException("Atleta não encontrado com o id: " + atletaId));

        atletaRepository.deleteById(atleta.getId());
    }
}
