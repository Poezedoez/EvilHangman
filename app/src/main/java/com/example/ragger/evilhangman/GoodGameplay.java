package com.example.ragger.evilhangman;


import java.util.List;
import java.util.Random;

public class GoodGameplay extends AbstractGameplay implements Gameplay {

    public GoodGameplay(Settings settings, WordLoader wordLoader) {
        super(settings, wordLoader);
        word = wordLoader.getRandomWordWithLength(wordLength);
    }
}
