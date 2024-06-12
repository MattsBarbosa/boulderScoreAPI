package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDto;
import com.mattsbarbosa.boulderScoreAPI.services.BoulderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/boulders")
public class BoulderController {

    private BoulderService boulderService;

    @PostMapping
    public ResponseEntity<BoulderDto> criarBoulder(@RequestBody BoulderDto boulderDto){
        BoulderDto boulderSalvo = boulderService.criarBoulder(boulderDto);
        return new ResponseEntity<>(boulderSalvo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoulderDto> pegarBoulderPorId(@PathVariable("id") Long boulderId){
        BoulderDto boulderDto = boulderService.pegarBoulderPorId(boulderId);
        return ResponseEntity.ok(boulderDto);
    }

    @GetMapping
    public ResponseEntity<List<BoulderDto>> pegarTodosBoulders(){
        List<BoulderDto> listaBoulders = boulderService.pegarTodosBoulders();
        return ResponseEntity.ok(listaBoulders);
    }

    @PutMapping("{id}")
    public ResponseEntity<BoulderDto> atualizarBoulder(@PathVariable("id") Long boulderId,
                                                       @RequestBody BoulderDto boulderAtualizado){
        BoulderDto boulderDto = boulderService.atualizarBoulder(boulderId, boulderAtualizado);
        return ResponseEntity.ok(boulderDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarBoulder(@PathVariable("id") Long boulderId){
        boulderService.deletaBoulder(boulderId);
        return ResponseEntity.ok("Boulder deletado com Sucesso");
    }
}
