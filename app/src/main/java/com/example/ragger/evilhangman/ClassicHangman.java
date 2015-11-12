package com.example.ragger.evilhangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ClassicHangman extends AppCompatActivity implements Gameplay {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
