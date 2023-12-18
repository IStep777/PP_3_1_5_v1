package ru.kata.spring.boot_security.demo.services;

public class NoUserException extends RuntimeException {
    public NoUserException(String message) {
        super(message);
    }
}
