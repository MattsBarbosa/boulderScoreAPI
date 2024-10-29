package com.mattsbarbosa.boulderScoreAPI.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(CustomBadRequestException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,
                HttpStatus.BAD_REQUEST,
                "Os dados fornecidos não são válidos");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomDatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseError(CustomDatabaseException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro ao executar a operação no banco de dados");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomInternalServerException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(CustomInternalServerException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno no servidor");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(CustomResourceNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,
                HttpStatus.NOT_FOUND,
                "Recurso não encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,
                HttpStatus.BAD_REQUEST,
                "Não foi possível salvar a entidade a restrições de integridade");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccess(DataAccessException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro ao acessar o banco de dados");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.create(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro inesperado interno no servidor");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Extrair e mapear cada erro de campo com a mensagem correspondente
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
