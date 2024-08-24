package com.krieger.document.manager.exception;

public class DocumentManagerInvalidInput extends RuntimeException {
    public DocumentManagerInvalidInput(String message) {
        super(message);
    }
    public DocumentManagerInvalidInput(String message, Throwable cause) {
        super(message, cause);
    }
}
