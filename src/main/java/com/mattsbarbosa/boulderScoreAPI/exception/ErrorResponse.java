package com.mattsbarbosa.boulderScoreAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private String detail;
    private HttpStatus status;

    public static ErrorResponse create(Exception ex, HttpStatus status, String customMessage){
        return new ErrorResponse(customMessage, ex.getMessage(), status);
    }

}
