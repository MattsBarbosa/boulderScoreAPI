package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.services.IAtletaBoulderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/atleta-boulder")
public class AtletaBoulderController {

    private final IAtletaBoulderService AtletaBoulderService;

    @GetMapping("{id}")
    public ResponseEntity<List<AtletaBoulderDTO>> getAllBouldersFromAtleta(@PathVariable("id") UUID atletaId){
        return ResponseEntity.ok(AtletaBoulderService.getAllBouldersFromAtleta(atletaId));
    }

    @PostMapping("{id}")
    public ResponseEntity<String> recordAttempt(@PathVariable("id") UUID atletaBoulderId){
        AtletaBoulderService.recordAttempt(atletaBoulderId);
        return ResponseEntity.ok("Tentativa adicionada com sucesso");
    }

    @PostMapping("sent/{id}")
    public ResponseEntity<String> recordSend(@PathVariable("id") UUID atletaBoulderId){
        AtletaBoulderService.recordSend(atletaBoulderId);
        return ResponseEntity.ok("Boulder encadenado");
    }

}
