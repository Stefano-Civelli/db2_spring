package it.polimi.db2_spring.exceptions;

public class OrderCreationException extends Exception {
    private static final long serialVersionUID = 1L;

    public OrderCreationException(String message) {
        super(message);
    }
}