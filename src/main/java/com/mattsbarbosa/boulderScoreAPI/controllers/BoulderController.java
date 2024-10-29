package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.BoulderDTO;
import com.mattsbarbosa.boulderScoreAPI.services.IBoulderService;
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
@RequestMapping("/api/boulders")
public class BoulderController {

    private final IBoulderService BoulderService;

    @PostMapping
    public ResponseEntity<BoulderDTO> saveBoulder(@Valid @RequestBody BoulderDTO boulderDto){
        return new ResponseEntity<>(BoulderService.saveBoulder(boulderDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoulderDTO> getBoulderById(@PathVariable("id") UUID boulderId){
        return ResponseEntity.ok(BoulderService.getBoulderById(boulderId));
    }

    @GetMapping
    public ResponseEntity<List<BoulderDTO>> getAllBoulders(){
        return ResponseEntity.ok(BoulderService.getAllBoulders());
    }

    @PutMapping("{id}")
    public ResponseEntity<BoulderDTO> updateBoulder(@PathVariable("id") UUID boulderId,
                                                    @Valid @RequestBody BoulderDTO boulderAtualizado){
        return ResponseEntity.ok(BoulderService.updateBoulder(boulderId, boulderAtualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBoulder(@PathVariable("id") UUID boulderId){
        BoulderService.deleteBoulder(boulderId);
        return ResponseEntity.ok("Boulder deletado com sucesso");
    }

    @PostMapping("/assign-to-atletas")
    public ResponseEntity<String> assignExistingBouldersToAllAtletas(@RequestBody List<UUID> boulderIds){
        BoulderService.assignBouldersToAllAtletas(boulderIds);
        return ResponseEntity.ok("Boulders adicionados a todos atletas com sucesso");
    }
}
