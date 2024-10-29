package com.mattsbarbosa.boulderScoreAPI.exception;

public class CustomResourceNotFoundException extends RuntimeException{

    public CustomResourceNotFoundException(String message){
        super(message);
    }

    public CustomResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
