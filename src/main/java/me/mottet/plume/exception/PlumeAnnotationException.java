package me.mottet.plume.exception;

public class PlumeAnnotationException extends RuntimeException {

    public PlumeAnnotationException(String message) {
        super(message);
    }

    public PlumeAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }
}
