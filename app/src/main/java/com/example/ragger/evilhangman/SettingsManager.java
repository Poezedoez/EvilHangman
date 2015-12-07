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

    private boolean evilMode;
    private int wordLength;
    private int initialGuesses;

    public SettingsManager(Context context) {
        SharedPreferences database = loadDatabase(context);
        loadStoredPreferences(database, context);
    }

    /* Load the database that contains the user preferences */
    private SharedPreferences loadDatabase(Context context) {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        SharedPreferences database = PreferenceManager.getDefaultSharedPreferences(context);
        return database;
    }

    /* Load stored preferences that already contain default values other than the ones given here */
    private void loadStoredPreferences(SharedPreferences database, Context context) {
        this.evilMode = database.getBoolean(context.getString(R.string.preference_mode), false);
        this.wordLength = Integer.parseInt(database.getString(context.getString(R.string.preference_word_length), "0"));
        this.initialGuesses = Integer.parseInt(database.getString(context.getString(R.string.preference_initial_guesses), "0"));
    }

    public boolean getMode() {
        return evilMode;
    }

    public int getWordLength() {
        return wordLength;
    }

    public int getInitialGuesses() {
        return initialGuesses;
    }
}
