package com.example.ragger.evilhangman;

/**
 * Created by Ragger on 12-11-2015.
 */
public interface Gameplay {

    /* Returns the current to be guesses word as an array of chars */
    public char[] getWordCharacters();

    /* Returns the so far guessed characters array */
    public char[] getGuessesCharacters();

    /* Decrement the guesses count */
    public void decrementGuesses();

    /* Returns the amount of incorrect guesses the user can still make */
    public int getGuesses();
}
