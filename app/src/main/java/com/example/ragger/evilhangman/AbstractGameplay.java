package com.example.ragger.evilhangman;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ragger on 17-11-2015.
 * This class shares methods of Evil- and GoodGameplay
 */
public abstract class AbstractGameplay implements Gameplay {

    protected final WordLoader wordLoader;
    protected String word;
    protected char[] displayedCharacters;
    protected List<Character> guessedCharacters;
    protected Settings settings;
    protected int guesses;
    protected int wordLength;

    public AbstractGameplay(Settings settings, WordLoader wordLoader) {
        this.wordLoader = wordLoader;
        this.settings = settings;
        this.wordLength = settings.getWordLength();
        this.guesses = settings.getInitialGuesses();
        this.displayedCharacters = new char[wordLength];
        fillCharArray();
        this.guessedCharacters = new ArrayList<>();

    }

    private void fillCharArray() {
        for (int i = 0; i < displayedCharacters.length; i++) {
            displayedCharacters[i] = '*';
        }
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public List getGuessedCharacters() {
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

    @Override
    public void makeGuess(char c){
        if(validInput(c)) {
            guessedCharacters.add(c);
        }
        if(correctGuess(c)){
            updateDisplayedCharacters(c);
        }
        else {
            decrementGuesses();
        }
    }

    public boolean gameWon() {
        if(new String(displayedCharacters).equals(word)){
            return true;
        }
        return false;
    }

    public boolean gameLost() {
        if(guesses <= 0){
            return true;
        }
        return false;
    }

    protected boolean correctGuess(char c){
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == c){
                return true;
            }
        }
        return false;
    }


    public boolean validInput(char symbol){
        if(symbol >= 'a' && symbol <= 'z'){
            return true;
        }
        else {
            return false;
        }
    }

    private void updateDisplayedCharacters(char c) {
        for(int i = 0; i < displayedCharacters.length; i++) {
            if(word.charAt(i) == c){
                displayedCharacters[i]=c;
            }
        }
    }
}
