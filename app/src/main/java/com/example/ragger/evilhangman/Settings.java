package com.example.ragger.evilhangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings extends AppCompatActivity {

    SharedPreferences database;
    SharedPreferences.Editor databaseEditor;
    int mode;
    int wordLength;
    int initialGuesses;


    public Settings(){
        this.database = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        this.databaseEditor = database.edit();
        this.mode = database.getInt("mode", 1);
        this.wordLength = database.getInt("wordLength", 5);
        this.initialGuesses =  database.getInt("initialGuesses", 10);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public int getMode(){
        return mode;
    }

    public int getWordLength(){
        return wordLength;
    }

    public int getInitialGuesses(){
        return initialGuesses;
    }
}
