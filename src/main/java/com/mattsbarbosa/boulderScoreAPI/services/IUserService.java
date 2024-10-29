package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.UserDTO;

public interface IUserService {

    UserDTO register(UserDTO userDTO);

    String verify(UserDTO userDTO);
}
