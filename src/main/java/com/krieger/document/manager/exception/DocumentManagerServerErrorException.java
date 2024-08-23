package com.krieger.document.manager.exception;

public class DocumentManagerServerErrorException extends RuntimeException {
    public DocumentManagerServerErrorException(String message) {
        super(message);
    }

    public DocumentManagerServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
