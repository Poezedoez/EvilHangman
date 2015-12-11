package com.example.ragger.evilhangman.exception;

public class NoWordsWithGivenLengthException extends Exception {
    public NoWordsWithGivenLengthException(int wordLength) {
        super("No words in file with length: " + wordLength);
    }
}
