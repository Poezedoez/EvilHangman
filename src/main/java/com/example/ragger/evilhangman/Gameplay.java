package com.example.ragger.evilhangman;

import com.example.ragger.evilhangman.exception.IllegalGuessException;

import java.io.Serializable;
import java.util.List;

/**
 * This is the interface for the Hangman game. In this Game the player should guess a word.
 * The user can only guess one character at a time. The correctly and incorrectly guessed characters
 * are displayed to the user, as well as the amount of guesses left.
 *
 * The game ends when the user is out of guesses or has guessed the word chosen by the computer.
 * When the game has ended, an appropriate score is calculated.
 *
 * @author Ragger
 */

public interface Gameplay extends Serializable {

    /**
     * Returns a list of all the guessed characters by the user
     */
    List getGuessedCharacters();

    /**
     * Returns the current state of the to be guessed word
     */
    char[] getDisplayedCharacters();

    /**
     * Returns the amount of incorrect guesses the user can still make
     */
    int getGuessesLeft();

    /**
     * Guess a character.
     *
     * @param input valid character in from range a-z
     * @throws IllegalGuessException when input not in range a-z
     */
    void guess(char input) throws IllegalGuessException;

    /**
     * Returns true if the user has guessed the word
     */
    boolean isWon();

    /**
     * Returns true if the user is out of guesses
     */
    boolean isLost();

    /**
     *  Return the score achieved in the current game
     */
    int getScore();

}
