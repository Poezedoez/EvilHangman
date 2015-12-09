package com.example.ragger.evilhangman.exception;

public class IllegalGuessException extends Exception {
    public IllegalGuessException(char input) {
        super("Invalid input: " + input);
    }
}
