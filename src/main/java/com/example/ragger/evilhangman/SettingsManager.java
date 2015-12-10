package com.example.ragger.evilhangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

/*
 * The SettingsManager contains all the current user setting preferences.
 * Other classes are able to ask these settings from this class
 */

public class SettingsManager implements Serializable {

    private boolean isEvilMode;
    private int wordLength;
    private int initialGuesses;

    public SettingsManager(Context context) {
        SharedPreferences database = loadDatabase(context);
        loadStoredPreferences(database, context);
    }

    /* Load the database that contains the user preferences */
    private SharedPreferences loadDatabase(Context context) {
        setDefaultPreferences(context);
        SharedPreferences database = PreferenceManager.getDefaultSharedPreferences(context);
        return database;
    }

    /* Load stored preferences that already contain default values other than the ones given here */
    private void loadStoredPreferences(SharedPreferences database, Context context) {
        this.isEvilMode = database.getBoolean(context.getString(R.string.preference_mode), false);
        this.wordLength = database.getInt(context.getString(R.string.preference_word_length), 5);
        this.initialGuesses = database.getInt(context.getString(R.string.preference_initial_guesses), 10);
    }

    public boolean getMode() {
        return isEvilMode;
    }

    public int getWordLength() {
        return wordLength;
    }

    public int getInitialGuesses() {
        return initialGuesses;
    }

    public void setDefaultPreferences(Context context){
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
    }
}
