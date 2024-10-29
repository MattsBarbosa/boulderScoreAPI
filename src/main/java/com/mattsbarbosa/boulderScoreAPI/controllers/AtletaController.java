package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaDTO;
import com.mattsbarbosa.boulderScoreAPI.services.IAtletaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/atletas")
public class AtletaController {

    private final IAtletaService AtletaService;

    @PostMapping
    public ResponseEntity<AtletaDTO> saveAtleta(@Valid @RequestBody AtletaDTO atletaDto){
        return new ResponseEntity<>(AtletaService.saveAtleta(atletaDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AtletaDTO> getAtletaById(@PathVariable("id") UUID atletaId){
        return ResponseEntity.ok(AtletaService.getAtletaById(atletaId));
    }

    @GetMapping
    public ResponseEntity<List<AtletaDTO>> getAllAtletas(){
        return ResponseEntity.ok(AtletaService.getAllAtletas());
    }

    @PutMapping("{id}")
    public ResponseEntity<AtletaDTO> updateAtleta(@PathVariable("id") UUID atletaId,
                                                     @Valid @RequestBody AtletaDTO atletaAtualizado){
        return ResponseEntity.ok(AtletaService.updateAtleta(atletaId, atletaAtualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAtleta(@PathVariable("id") UUID atletaId){
        AtletaService.deleteAtleta(atletaId);
        return ResponseEntity.ok("Atleta deletado com sucesso");
    }
}
