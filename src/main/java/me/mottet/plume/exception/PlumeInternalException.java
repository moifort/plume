package me.mottet.plume.exception;

public class PlumeInternalException extends RuntimeException {

    public PlumeInternalException(String message) {
        super(message);
    }

    public PlumeInternalException(String message, Throwable cause) {
        super(message, cause);
    }
}
