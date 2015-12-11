package com.example.ragger.evilhangman;

import com.example.ragger.evilhangman.exception.IllegalGuessException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class shares methods of Evil- and GoodGameplay, meaning that methods in this class
 * can be used by the two classes just the same. There are no specific methods in this class that
 * are only useful to one particular game mode.
 *
 * @author Ragger
 */
public abstract class AbstractGameplay implements Gameplay {

    protected String word;
    protected char[] displayedCharacters;
    protected List<Character> guessedCharacters;
    protected int guessesLeft;
    protected int wordLength;


    public AbstractGameplay(SettingsManager settingsManager) {
        this.wordLength = settingsManager.getWordLength();
        this.guessesLeft = settingsManager.getInitialGuesses();
        this.guessedCharacters = new ArrayList<>();
        fillCharArray();


    }

    /* Initialize the array of characters of the word revealed for the user */
    private void fillCharArray() {
        this.displayedCharacters = new char[wordLength];
        for (int i = 0; i < displayedCharacters.length; i++) {
            displayedCharacters[i] = '*';
        }
    }

    @Override
    public List<Character> getGuessedCharacters() {
        return guessedCharacters;
    }

    @Override
    public char[] getDisplayedCharacters() {
        return displayedCharacters;
    }

    @Override
    public int getGuessesLeft() {
        return guessesLeft;
    }

    @Override
    public void guess(char input) throws IllegalGuessException {
        validate(input);
        if (!guessedCharacters.contains(input)) {
            guessedCharacters.add(input);
        }
        if (wordContains(input)) {
            updateDisplayedCharacters(input);
        } else {
            guessesLeft--;
        }
    }

    /* Returns true if the word is guessed, which means a win */
    @Override
    public boolean isWon() {
        if (new String(displayedCharacters).equals(word)) {
            return true;
        } else {
            return false;
        }
    }

    /* Returns true if the user is out of guesses, which means a loss */
    @Override
    public boolean isLost() {
        if (guessesLeft <= 0) {
            return true;
        } else {
            return false;
        }

    }

    /* Returns the highscore based guesses needed, the word length and the game mode */
    public int getScore() {
        return (27 - guessedCharacters.size()) * wordLength;
    }

    /* Throws exception when the user input is invalid */
    protected void validate(char input) throws IllegalGuessException {
        if (!Character.isLetter(input)) {
            throw new IllegalGuessException(input);
        }
    }

    /* Returns true if the current word contains the guessed character */
    private boolean wordContains(char c) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    /* Updates the array with character of the word revealed to the user */
    private void updateDisplayedCharacters(char c) {
        for (int i = 0; i < displayedCharacters.length; i++) {
            if (word.charAt(i) == c) {
                displayedCharacters[i] = c;
            }
        }
    }
}
