package com.example.ragger.evilhangman;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ragger on 17-11-2015.
 * This class shares methods of Evil- and GoodGameplay, meaning that methods in this class
 * can be used by the two classes just the same. There are no specific methods in this class that
 * are only useful to one particular game mode.
 */
public abstract class AbstractGameplay implements Gameplay {

    protected String word;
    protected char[] displayedCharacters;
    protected List<Character> guessedCharacters;
    protected SettingsManager settingsManager;
    protected int guesses;
    protected int incorrectGuesses;
    protected int wordLength;

    public AbstractGameplay(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
        this.wordLength = settingsManager.getWordLength();
        this.guesses = settingsManager.getInitialGuesses();
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
    public String getWord() {
        return word;
    }

    @Override
    public List<Character> getGuessedCharacters() {
        return guessedCharacters;
    }

    @Override
    public char[] getDisplayedCharacters(){
        return displayedCharacters;
    }

    @Override
    public void decrementGuesses() {
        guesses--;
    }

    @Override
    public int getGuesses() {
        return guesses;
    }

    /* Handles a game move that consists of the user giving an input character */
    @Override
    public void makeGuess(char c){
        if(!guessedCharacters.contains(c)){
            guessedCharacters.add(c);
        }
        if(correctGuess(c)){
            updateDisplayedCharacters(c);
        }
        else {
            incorrectGuesses++;
            decrementGuesses();
        }
    }

    /* Returns true if the word is guessed, which means a win */
    @Override
    public boolean gameWon() {
        if(new String(displayedCharacters).equals(word)){
            return true;
        } else {
            return false;
        }
    }

    /* Returns true if the user is out of guesses, which means a loss */
    @Override
    public boolean gameLost() {
        if(guesses <= 0){
            return true;
        } else {
            return false;
        }

    }

    /* Returns true if the current word contains the guessed character */
    protected boolean correctGuess(char c){
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == c){
                return true;
            }
        }
        return false;
    }

    /* Returns true if the user input is a (latin) letter */
    @Override
    public boolean validInput(CharSequence s){
        if(s.toString().equals("")){
            return false;
        }
        char c = s.charAt(0);
        if(Character.isLetter(c)){
            return true;
        } else {
            return false;
        }

    }

    /* Updates the array with character of the word revealed to the user */
    private void updateDisplayedCharacters(char c) {
        for(int i = 0; i < displayedCharacters.length; i++) {
            if(word.charAt(i) == c){
                displayedCharacters[i]=c;
            }
        }
    }

    /* Returns the highscore based guesses needed and the word length */
    public int calculateHighscore(){
        return (27-incorrectGuesses)*(wordLength*2);
    }
}
