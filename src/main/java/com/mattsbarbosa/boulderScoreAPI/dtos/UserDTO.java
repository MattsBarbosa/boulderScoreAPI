package com.mattsbarbosa.boulderScoreAPI.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @NotBlank(message = "O username não pode ser vazio")
    private String username;

    @NotBlank(message = "A senha não pode ser vazia")
    private String password;
}
