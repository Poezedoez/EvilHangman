package com.example.ragger.evilhangman.exception;

/**
 * Created by Ragger on 9-12-2015.
 */
public class NoWordsWithGivenLengthException extends Exception {
    public NoWordsWithGivenLengthException(int wordLength) {
        super("No words in file with length: " + wordLength);
    }
}
