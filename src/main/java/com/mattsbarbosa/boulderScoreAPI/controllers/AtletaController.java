package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/atletas")
public class AtletaController {

    private final AtletaService atletaService;

    @PostMapping
    public ResponseEntity<AtletaDTO> saveAtleta(@RequestBody AtletaDTO atletaDto){
        return new ResponseEntity<>(atletaService.saveAtleta(atletaDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AtletaDTO> getAtletaById(@PathVariable("id") UUID atletaId){
        return ResponseEntity.ok(atletaService.getAtletaById(atletaId));
    }

    @GetMapping
    public ResponseEntity<List<AtletaDTO>> getAllAtletas(){
        return ResponseEntity.ok(atletaService.getAllAtletas());
    }

    @PutMapping("{id}")
    public ResponseEntity<AtletaDTO> updateAtleta(@PathVariable("id") UUID atletaId,
                                                     @RequestBody AtletaDTO atletaAtualizado){
        return ResponseEntity.ok(atletaService.updateAtleta(atletaId, atletaAtualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAtleta(@PathVariable("id") UUID atletaId){
        atletaService.deleteAtleta(atletaId);
        return ResponseEntity.ok("Atleta deletado com sucesso");
    }
}
