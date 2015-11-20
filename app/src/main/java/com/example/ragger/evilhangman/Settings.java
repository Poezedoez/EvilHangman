package com.example.ragger.evilhangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
// public field parameters or getters?
public class Settings extends AppCompatActivity {

    SharedPreferences database;
    SharedPreferences.Editor databaseEditor;
    boolean evilMode;
    int wordLength;
    int initialGuesses;


    public Settings(Context context){
        this.database = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        this.databaseEditor = database.edit();
        this.evilMode = database.getBoolean("mode", false);
        this.wordLength = database.getInt("wordLength", 5);
        this.initialGuesses =  database.getInt("initialGuesses", 10);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public boolean getMode(){
        return evilMode;
    }

    public int getWordLength(){
        return wordLength;
    }

    public int getInitialGuesses(){
        return initialGuesses;
    }
}
