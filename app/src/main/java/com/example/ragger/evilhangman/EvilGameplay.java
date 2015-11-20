package com.example.ragger.evilhangman;


import java.util.List;

public class EvilGameplay extends AbstractGameplay implements Gameplay {

    List subset;

    public EvilGameplay(Settings settings, WordLoader wordLoader) {
        super(settings, wordLoader);
        word = "";
        this.subset = wordLoader.getAllWordsWithLength(wordLength);
    }

    @Override
    protected boolean correctGuess(char c){
        // If no word is chosen yet, see if we must do so now
        if(word.isEmpty()) {
            word = narrowSubset(c);
        }
        
        // If still no word has to be chosen, we can count it as an incorrect guess
        if(word.isEmpty()) {
            return false;
        }

        // We have a chosen word, so we have to do the regular check
        return super.correctGuess(c);
    }

    private String narrowSubset(char c) {
        return "";
    }
}
