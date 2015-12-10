package com.example.ragger.evilhangman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoresActivity extends AppCompatActivity {

    List<Integer> scores;
    ListView list;
    SharedPreferences database;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    CustomAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        // Get database
        this.database = getSharedPreferences("highscores", MODE_PRIVATE);
        this.editor = database.edit();

        // Prepare highscores
        this.scores = getHighscores();
        addNewScore();
        arrangeHighscores();
        storeHighscores();

        // Prepare layout
        this.toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Use adapter object to instantiate list entries
        this.list = (ListView) findViewById(R.id.lvScores);
        this.adapter = new CustomAdapter(this, this.scores);
        list.setAdapter(adapter);
    }

    /* Add a the score from last game to the highscores list */
    private void addNewScore() {

        // Add score from last game
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        scores.add(score);

        // Set view to last game's score
        TextView tvLastScore = (TextView) findViewById(R.id.tvLastScore);
        tvLastScore.setText(String.valueOf(score));
    }

    /* Get the 10 highest saved scores */
    private List<Integer> getHighscores() {
        this.scores = new ArrayList<>();
        int score;
        for (int i = 0; i < 10; i++) {
            score = database.getInt("highscore" + i, 0);
            scores.add(score);
        }
        return this.scores;
    }

    /* Sort the highscores in descending order */
    private void arrangeHighscores() {
        Collections.sort(scores);
        Collections.reverse(scores);
        scores.remove(scores.size() - 1);

    }

    /* Store the 10 highest scores (with the new highscore) */
    private void storeHighscores() {
        int score;
        for (int i = 0; i < 10; i++) {
            score = scores.get(i);
            editor.putInt("highscore" + i, score);
            editor.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.miClear) {
            // put dialog
            clearHighscores();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearHighscores() {
        editor.clear();
        editor.commit();
        scores.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent newGame = new Intent(this, HangmanActivity.class);
        finish();
        startActivity(newGame);
    }
}

class CustomAdapter extends ArrayAdapter<Integer> {

    Context context;
    List<Integer> scores;

    CustomAdapter(Context c, List<Integer> scores) {
        super(c, R.layout.layout_highscore_entry, scores);
        this.context = c;
        this.scores = scores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get the entry layout and objects
        View entry = inflater.inflate(R.layout.layout_highscore_entry, parent, false);
        TextView tvEntryNumber = (TextView) entry.findViewById(R.id.tvEntryNumber);
        TextView tvScore = (TextView) entry.findViewById(R.id.tvScore);

        // set the score in the entry
        tvEntryNumber.setText(String.valueOf(position + 1));
        tvScore.setText(String.valueOf(scores.get(position)));

        return entry;
    }
}
