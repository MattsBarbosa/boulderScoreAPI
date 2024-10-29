package com.mattsbarbosa.boulderScoreAPI.controllers;

import com.mattsbarbosa.boulderScoreAPI.dtos.UserDTO;
import com.mattsbarbosa.boulderScoreAPI.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.register(userDTO),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDTO userDTO){
            String jwtToken = userService.verify(userDTO);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
