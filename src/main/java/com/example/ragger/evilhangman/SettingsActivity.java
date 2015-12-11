package com.example.ragger.evilhangman;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity{

    Toolbar toolbar;
    int minWordLength;
    int maxWordLength;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadActionBar();

        // Set preferences fragment layout
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment())
                .commit();

        // Get setting limits
        Intent intent = getIntent();
        this.minWordLength = intent.getIntExtra("minWordLength", -1);
        this.maxWordLength = intent.getIntExtra("maxWordLength", -1);
    }

    private void loadActionBar() {
        this.toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
