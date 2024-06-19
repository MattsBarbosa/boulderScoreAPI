package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.services.BoulderService;
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
@RequestMapping("/api/boulders")
public class BoulderController {

    private final BoulderService boulderService;

    @PostMapping
    public ResponseEntity<BoulderDTO> saveBoulder(@RequestBody BoulderDTO boulderDto){
        return new ResponseEntity<>(boulderService.saveBoulder(boulderDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoulderDTO> getBoulderById(@PathVariable("id") UUID boulderId){
        return ResponseEntity.ok(boulderService.getBoulderById(boulderId));
    }

    @GetMapping
    public ResponseEntity<List<BoulderDTO>> getAllBoulders(){
        return ResponseEntity.ok(boulderService.getAllBoulders());
    }

    @PutMapping("{id}")
    public ResponseEntity<BoulderDTO> updateBoulder(@PathVariable("id") UUID boulderId,
                                                       @RequestBody BoulderDTO boulderAtualizado){
        return ResponseEntity.ok(boulderService.updateBoulder(boulderId, boulderAtualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBoulder(@PathVariable("id") UUID boulderId){
        boulderService.deleteBoulder(boulderId);
        return ResponseEntity.ok("Boulder deletado com sucesso");
    }

    @PostMapping("/assign-to-atletas")
    public ResponseEntity<String> assignExistingBouldersToAllAtletas(@RequestBody List<UUID> boulderIds){
        boulderService.assignBouldersToAllAtletas(boulderIds);
        return ResponseEntity.ok("Boulders adicionados a todos atletas com sucesso");
    }
}
