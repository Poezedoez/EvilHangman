package com.example.ragger.evilhangman;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Ragger on 26-11-2015.
 * This class makes preference fragment based on the preferences xml file
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
