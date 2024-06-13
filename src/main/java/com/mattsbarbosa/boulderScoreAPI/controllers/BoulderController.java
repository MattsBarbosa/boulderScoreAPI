package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;
import com.mattsbarbosa.boulderScoreAPI.services.BoulderService;
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
@RequestMapping("/api/boulders")
public class BoulderController {

    private BoulderService boulderService;

    @PostMapping
    public ResponseEntity<BoulderDto> criarBoulder(@RequestBody BoulderDto boulderDto){
        BoulderDto boulderSalvo = boulderService.criarBoulder(boulderDto);
        return new ResponseEntity<>(boulderSalvo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoulderDto> pegarBoulderPorId(@PathVariable("id") UUID boulderId){
        BoulderDto boulderDto = boulderService.pegarBoulderPorId(boulderId);
        return ResponseEntity.ok(boulderDto);
    }

    @GetMapping
    public ResponseEntity<Set<BoulderDto>> pegarTodosBoulders(){
        Set<BoulderDto> listaBoulders = boulderService.pegarTodosBoulders();
        return ResponseEntity.ok(listaBoulders);
    }

    @PutMapping("{id}")
    public ResponseEntity<BoulderDto> atualizarBoulder(@PathVariable("id") UUID boulderId,
                                                       @RequestBody BoulderDto boulderAtualizado){
        BoulderDto boulderDto = boulderService.atualizarBoulder(boulderId, boulderAtualizado);
        return ResponseEntity.ok(boulderDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarBoulder(@PathVariable("id") UUID boulderId){
        boulderService.deletaBoulder(boulderId);
        return ResponseEntity.ok("Boulder deletado com Sucesso");
    }
}
