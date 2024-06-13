package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/atletas")
public class AtletaController {

    private AtletaService atletaService;

    @PostMapping
    public ResponseEntity<AtletaDto> criarAtleta(@RequestBody AtletaDto atletaDto){
        AtletaDto atletaSalvo = atletaService.criarAtleta(atletaDto);
        return new ResponseEntity<>(atletaSalvo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AtletaDto> pegarAtletaPorId(@PathVariable("id") UUID atletaId){
        AtletaDto atletaDto = atletaService.pegarAtletaPorId(atletaId);
        return ResponseEntity.ok(atletaDto);
    }

    @GetMapping
    public ResponseEntity<Set<AtletaDto>> pegarTodosAtletas(){
        Set<AtletaDto> listaAtletas = atletaService.pegarTodosAtletas();
        return ResponseEntity.ok(listaAtletas);
    }

    @PutMapping("{id}")
    public ResponseEntity<AtletaDto> atualizarAtleta(@PathVariable("id") UUID atletaId,
                                                     @RequestBody AtletaDto atletaAtualizado){
        AtletaDto atletaDto = atletaService.atualizarAtleta(atletaId, atletaAtualizado);
        return ResponseEntity.ok(atletaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarAtleta(@PathVariable("id") UUID atletaId){
        atletaService.deletaAtleta(atletaId);
        return ResponseEntity.ok("Atleta deletado com Sucesso");
    }
}
