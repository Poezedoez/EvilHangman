package com.example.ragger.evilhangman;


import java.util.List;

/**
 * This class is instantiated when the user has opted to go for the classic game mode.
 * This gameplay version chooses a word at random and doesn't deviate from it. The basic methods
 * to play the game are in its superclass AbstractGameplay
 */

public class GoodGameplay extends AbstractGameplay {

    public GoodGameplay(SettingsManager settingsManager, WordManager wordManager) {
        super(settingsManager);
        super.word = wordManager.getRandomWordWithLength(wordLength);
    }

    // TO DELETE
    @Override
    public List<String> getSubset() {
        return null;
    }
}
