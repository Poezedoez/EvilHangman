package com.example.ragger.evilhangman;


import com.example.ragger.evilhangman.exception.NoWordsWithGivenLengthException;

import java.util.List;

/**
 * This class is instantiated when the user has opted to go for the classic game mode.
 * This gameplay version chooses a word at random and doesn't deviate from it. The basic methods
 * to play the game are in its superclass AbstractGameplay
 *
 * @author Ragger
 */

public class GoodGameplay extends AbstractGameplay {

    public GoodGameplay(SettingsManager settingsManager, WordManager wordManager) throws NoWordsWithGivenLengthException {
        super(settingsManager);
        word = wordManager.getRandomWordWithLength(wordLength);
    }
}
