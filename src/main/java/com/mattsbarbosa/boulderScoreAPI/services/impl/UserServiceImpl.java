package com.mattsbarbosa.boulderScoreAPI.services.impl;

import com.mattsbarbosa.boulderScoreAPI.dtos.UserDTO;
import com.mattsbarbosa.boulderScoreAPI.repositories.UserRepository;
import com.mattsbarbosa.boulderScoreAPI.services.BaseService;
import com.mattsbarbosa.boulderScoreAPI.services.IUserService;
import com.mattsbarbosa.boulderScoreAPI.services.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseService<UserDTO> implements IUserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public UserDTO register(UserDTO userDTO) {
        validateDto(userDTO);
        return myMapper.toUserDTO(userRepository.save(myMapper.toUserEntity(userDTO)));
    }

    @Override
    public String verify(UserDTO userDTO) {
        validateDto(userDTO);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()));
        return jwtService.generateToken(userDTO.getUsername());
    }
}
