package com.example.ragger.evilhangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class EvilHangman extends AppCompatActivity implements Gameplay {

    Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.settings = new Settings();

    }

    @Override
    public char[] getWordCharacters() {
        return new char[0];
    }

    @Override
    public char[] getGuessesCharacters() {
        return new char[0];
    }

    @Override
    public void decrementGuesses() {

    }

    @Override
    public int getGuesses() {
        return 0;
    }
}
