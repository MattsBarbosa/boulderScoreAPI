package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.dtos.UserDTO;
import org.apache.tomcat.websocket.AuthenticationException;

public interface IUserService {

    public UserDTO register(UserDTO userDTO);

    public String verify(UserDTO userDTO);
}
