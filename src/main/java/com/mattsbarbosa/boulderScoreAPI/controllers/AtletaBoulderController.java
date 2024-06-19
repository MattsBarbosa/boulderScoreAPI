package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.AtletaBoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.services.AtletaBoulderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/atleta-boulder")
public class AtletaBoulderController {

    private final AtletaBoulderService atletaBoulderService;

    @GetMapping("{id}")
    public ResponseEntity<List<AtletaBoulderDTO>> getAllBouldersFromAtleta(@PathVariable("id") UUID atletaId){
        return ResponseEntity.ok(atletaBoulderService.getAllBouldersFromAtleta(atletaId));
    }

    @PostMapping("{id}")
    public ResponseEntity<String> recordAttempt(@PathVariable("id") UUID atletaBoulderId){
        atletaBoulderService.recordAttempt(atletaBoulderId);
        return ResponseEntity.ok("Tentativa adicionada com sucesso");
    }

    @PostMapping("sent/{id}")
    public ResponseEntity<String> recordSend(@PathVariable("id") UUID atletaBoulderId){
        atletaBoulderService.recordSend(atletaBoulderId);
        return ResponseEntity.ok("Boulder encadenado");
    }

}
