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

    //Custom exception to handle when a record is not found
    @ExceptionHandler(value = { DocumentManagerNotFoundException.class })
    public ResponseEntity<?> handleNotFoundException(DocumentManagerNotFoundException exception) {
        DocumentManagerException documentManagerException = new DocumentManagerException(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(documentManagerException);
    }

    // Generic exception handler to display custom messages based on the use case
    @ExceptionHandler(value = { DocumentManagerServerErrorException.class })
    public ResponseEntity<?> handleGenericException(DocumentManagerServerErrorException exception) {
        DocumentManagerException documentManagerException = new DocumentManagerException(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(documentManagerException);
    }

    @ExceptionHandler(DocumentManagerInvalidInput.class)
    public ResponseEntity<?> handleInvalidInput(DocumentManagerInvalidInput exception) {
        DocumentManagerException documentManagerException = new DocumentManagerException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(documentManagerException);
    }

    // This method capture when an input value is invalid based on the validations in the DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }
}
