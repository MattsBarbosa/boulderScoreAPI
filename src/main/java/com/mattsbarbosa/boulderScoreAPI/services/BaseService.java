package com.mattsbarbosa.boulderScoreAPI.services;

import com.mattsbarbosa.boulderScoreAPI.exception.CustomBadRequestException;
import com.mattsbarbosa.boulderScoreAPI.mappers.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T> {

    @Autowired
    protected MyMapper myMapper;

    protected void validateDto(T dto) {
        if (dto == null) {
            throw new CustomBadRequestException("O DTO não pode ser nulo");
        }
    }
}
