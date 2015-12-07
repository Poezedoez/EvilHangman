package com.example.ragger.evilhangman;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ragger on 12-11-2015.
 */
public interface Gameplay extends Serializable {

     /* Returns the current to be guesses word as a String */
    String getWord();

    /* Returns a list of all the guessed letters by the user */
    List getGuessedCharacters();

    /* Returns the current state of the to be guessed word */
    char[] getDisplayedCharacters();

    /* Decrement the guesses count */
    void decrementGuesses();

    /* Returns the amount of incorrect guesses the user can still make */
    int getGuesses();

    /* Guess a character */
    void makeGuess(char c);

    /* Return true if the game is won */
    boolean gameWon();

    /* Return true if the game is lost */
    boolean gameLost();

    /* Return true if the game accepts the input */
    boolean validInput(CharSequence s);

    /* Calculate the highscore achieved in the current game */
    int calculateHighscore();

    // TO DELETE
    List<String> getSubset();
}
