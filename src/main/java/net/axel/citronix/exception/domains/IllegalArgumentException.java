package net.axel.citronix.exception.domains;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String entityName, Object attr) {
        super(attr + " can't be null for the " + entityName);
    }

    public IllegalArgumentException(String message) {
        super(message);
    }
}
