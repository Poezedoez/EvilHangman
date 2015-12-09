package com.example.ragger.evilhangman;


import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ragger.evilhangman.exception.IllegalGuessException;
import com.example.ragger.evilhangman.exception.NoWordsWithGivenLengthException;

import java.util.List;


/**
 * This is the launch activity of the Hangman game that shows all the game information
 * the user needs to play the game. From this activity can be navigated to the SettingsActivity
 * to change preferences. When the game is won, the user will be taken to the HighscoresActivity
 *
 * @author Ragger
 */

public class HangmanActivity extends AppCompatActivity {

    private SettingsManager settingsManager;
    private Gameplay game;
    private TextView tvGuesses, tvGuessedCharacters, tvDisplayedWord;
    private LinearLayout canvas;
    private ImageView ivHangman;
    private Toolbar toolbar;
    private static int[] images = {R.drawable.hangman0, R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3,
            R.drawable.hangman4, R.drawable.hangman5, R.drawable.hangman6};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        findViews();
        setSupportActionBar(toolbar);
        addKeyboard();
        startNewGame();
    }

    private void findViews() {
        this.tvGuesses = (TextView) findViewById(R.id.tvGuesses);
        this.tvGuessedCharacters = (TextView) findViewById(R.id.tvGuessedCharacters);
        this.tvDisplayedWord = (TextView) findViewById(R.id.tvDisplayedWord);
        this.ivHangman = (ImageView) findViewById(R.id.ivHangman);
        this.toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        this.canvas = (LinearLayout) findViewById(R.id.canvas);
    }

    private void addKeyboard() {

        // Create and attach the Keyboard to the view
        Keyboard keyboard = new Keyboard(this, R.xml.keyboard);
        KeyboardView kv = (KeyboardView) findViewById(R.id.keyboardview);
        kv.setKeyboard(keyboard);

        // Install the keyboard listener
        KeyboardListener keyboardListener = new KeyboardListener(this);
        kv.setOnKeyboardActionListener(keyboardListener);
        kv.setPreviewEnabled(false);
    }

    private void startNewGame() {
        loadSettings(this);
        loadGame();
        updateViews();
    }

    private void loadSettings(Context context) {
        this.settingsManager = new SettingsManager(context);
    }

    private void loadGame() {
        boolean evilModeEnabled = settingsManager.getMode();
        try {
            if (evilModeEnabled) {
                this.game = new EvilGameplay(settingsManager, new WordManager(this));
            } else {
                this.game = new GoodGameplay(settingsManager, new WordManager(this));
            }
        } catch (NoWordsWithGivenLengthException e) {
            showMessage("No words known with word length " + settingsManager.getWordLength(), Toast.LENGTH_LONG);
        }
    }

    private void updateViews() {

        // Update user guesses left
        String newGuesses = String.valueOf(game.getGuessesLeft());
        tvGuesses.setText(newGuesses);

        // Update currently displayed word
        String displayedWord = new String(game.getDisplayedCharacters());
        tvDisplayedWord.setText(displayedWord);

        // Update guessed characters
        StringBuilder sb = new StringBuilder();
        List<Character> guessedCharacters = game.getGuessedCharacters();
        for (Character c : guessedCharacters) {
            sb.append(c);
            sb.append("; ");
        }
        String charString = sb.toString();
        tvGuessedCharacters.setText(charString);

        // Update hangman image
        switchImage();
    }

    /* Switch hangman image based on number of guesses left */
    private void switchImage() {
        int guesses = game.getGuessesLeft();
        int image = images[6];
        switch (guesses) {
            case (5):
                image = images[5];
                break;
            case (4):
                image = images[4];
                break;
            case (3):
                image = images[3];
                break;
            case (2):
                image = images[2];
                break;
            case (1):
                image = images[1];
                break;
            case (0):
                image = images[0];
        }
        ivHangman.setImageResource(image);
    }

    /* Check if the game has finished and handle accordingly */
    private void checkGameState() {

        if (game.isWon()) {

            // Congratulate user
            showMessage(getString(R.string.game_message_won), Toast.LENGTH_LONG);

            // Go to highscores
            Intent highscores = new Intent(HangmanActivity.this, HighscoresActivity.class);
            int score = game.getScore();
            highscores.putExtra("score", score);
            finish();
            startActivity(highscores);
        }

        else if (game.isLost()) {

            // Hide canvas
            canvas.setVisibility(View.INVISIBLE);

            // Inform user
            showMessage(getString(R.string.game_message_lost), Toast.LENGTH_LONG);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hangman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.miSettings) {

            // Start SettingsActivity passing setting limits
            WordManager wordManager = new WordManager(this);
            Intent settingsActivity = new Intent(HangmanActivity.this, SettingsActivity.class);
            settingsActivity.putExtra("maxWordLength", wordManager.getLongestWordLength());
            settingsActivity.putExtra("minWordLength", wordManager.getShortestWordLength());
            startActivity(settingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game = (Gameplay) savedInstanceState.getSerializable("game");
        updateViews();
    }

    /* Show a toast */
    private void showMessage(String message, int duration) {
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /* Let the game react to a guess of the user */
    public void guess(char c) throws IllegalGuessException {
        game.guess(c);
        updateViews();
        checkGameState();
    }
}
