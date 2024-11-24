package ru.tbank.jdbc.example.repository.Exceptions;

public class InvalidResultsNumberException extends RuntimeException {
    public InvalidResultsNumberException(String message) {
        super(message);
    }
}
