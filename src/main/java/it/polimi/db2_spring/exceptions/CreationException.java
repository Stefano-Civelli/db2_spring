package it.polimi.db2_spring.exceptions;

public class CreationException extends Exception {
    private static final long serialVersionUID = 1L;

    public CreationException(String message) {
        super(message);
    }
}