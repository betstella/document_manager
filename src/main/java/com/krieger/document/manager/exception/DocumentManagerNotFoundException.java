package com.krieger.document.manager.exception;

public class DocumentManagerNotFoundException extends RuntimeException {
    public DocumentManagerNotFoundException(String message) {
        super(message);
    }
    public DocumentManagerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
