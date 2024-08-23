package com.krieger.document.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DocumentManagerExceptionHandler {

    @ExceptionHandler(value = { DocumentManagerNotFoundException.class })
    public ResponseEntity<?> handleNotFoundException(DocumentManagerNotFoundException exception) {
        DocumentManagerException documentManagerException = new DocumentManagerException(exception.getMessage(), exception, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(documentManagerException);
    }

    @ExceptionHandler(value = { DocumentManagerServerErrorException.class })
    public ResponseEntity<?> handleGenericException(DocumentManagerServerErrorException exception) {
        DocumentManagerException documentManagerException = new DocumentManagerException(exception.getMessage(), exception, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(documentManagerException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }
}
