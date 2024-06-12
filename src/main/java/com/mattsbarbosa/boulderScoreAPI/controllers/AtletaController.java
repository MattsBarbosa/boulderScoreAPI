package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDto;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/atletas")
public class AtletaController {

    private AtletaService atletaService;

    @PostMapping
    public ResponseEntity<AtletaDto> criarAtleta(@RequestBody AtletaDto atletaDto){
        AtletaDto atletaSalvo = atletaService.criarAtleta(atletaDto);
        return new ResponseEntity<>(atletaSalvo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AtletaDto> pegarAtletaPorId(@PathVariable("id") Long atletaId){
        AtletaDto atletaDto = atletaService.pegarAtletaPorId(atletaId);
        return ResponseEntity.ok(atletaDto);
    }

    @GetMapping
    public ResponseEntity<List<AtletaDto>> pegarTodosAtletas(){
        List<AtletaDto> listaAtletas = atletaService.pegarTodosAtletas();
        return ResponseEntity.ok(listaAtletas);
    }

    @PutMapping("{id}")
    public ResponseEntity<AtletaDto> atualizarAtleta(@PathVariable("id") Long atletaId,
                                                     @RequestBody AtletaDto atletaAtualizado){
        AtletaDto atletaDto = atletaService.atualizarAtleta(atletaId, atletaAtualizado);
        return ResponseEntity.ok(atletaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarAtleta(@PathVariable("id") Long atletaId){
        atletaService.deletaAtleta(atletaId);
        return ResponseEntity.ok("Atleta deletado com Sucesso");
    }
}
