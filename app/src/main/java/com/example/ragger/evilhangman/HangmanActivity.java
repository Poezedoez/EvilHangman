package com.example.ragger.evilhangman;


import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Ragger on 17-11-2015.
 * This is the main activity of the Hangman game. That shows all the game information
 * the user needs to play the game. From this activity can be navigated to the Settings
 * activity to change preferences. Upon game end, the user will be taken to the Highscore activity
 */
public class HangmanActivity extends AppCompatActivity {

    private SettingsManager settingsManager;
    private Gameplay game;
    private char[] displayedCharacters;
    private TextView tvGuesses, tvGuessedCharacters, tvWord, tvActualWord;
    private EditText etInput;
    private ImageView ivHangman;
    private Button bGuess;
    private Toolbar toolbar;
    private RelativeLayout gameLayout;
    private LinearLayout stringBox;
    private static int[] images = {R.drawable.hangman0, R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3,
            R.drawable.hangman4, R.drawable.hangman5, R.drawable.hangman6};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        findViews();
        setSupportActionBar(toolbar);
        startNewGame();

    }

    private void findViews() {
        this.tvGuesses = (TextView) findViewById(R.id.tvGuesses);
        this.tvGuessedCharacters = (TextView) findViewById(R.id.tvGuessedCharacters);
        this.tvWord = (TextView) findViewById(R.id.tvWord);
        this.tvActualWord = (TextView) findViewById(R.id.tvActualWord);
        this.etInput = (EditText) findViewById(R.id.etInput);
        this.ivHangman = (ImageView) findViewById(R.id.ivHangman);
        this.bGuess = (Button) findViewById(R.id.bGuess);
        this.gameLayout = (RelativeLayout) findViewById(R.id.game_layout);
        this.toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        this.stringBox = (LinearLayout) findViewById(R.id.llStringBox);
    }

    private void startNewGame() {
        loadSettings(this);
        loadGame();
        initializeViews();
    }

    private void loadSettings(Context context) {
        this.settingsManager = new SettingsManager(context);
    }

    private void loadGame() {
        boolean evilModeEnabled = settingsManager.getMode();
        if (evilModeEnabled) {
            this.game = new EvilGameplay(settingsManager, new WordManager(this));
        } else {
            this.game = new GoodGameplay(settingsManager, new WordManager(this));
        }
    }

    private void initializeViews() {
        this.displayedCharacters = game.getDisplayedCharacters();
        tvGuesses.setText(String.valueOf(settingsManager.getInitialGuesses()));
        bGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = etInput.getText();
                if (game.validInput(input)) {
                    game.makeGuess(Character.toLowerCase(input.charAt(0)));
                    updateViews();
                    checkGameState();
                } else {
                    showMessage(getString(R.string.game_message_input), Toast.LENGTH_SHORT);
                }
            }
        });
        tvWord.setText(String.valueOf(displayedCharacters));
//        addCells();
        addKeyboard();
    }

    private void addCells() {
        // Add cells to layout based on chosen word length
        for (int i = 0; i < settingsManager.getWordLength(); i++) {
            TextView cell = (TextView) getLayoutInflater().inflate(R.layout.layout_cell, null);
            stringBox.addView(cell);
        }
    }

    private void addKeyboard() {

        // Create the Keyboard
        Keyboard keyboard = new Keyboard(this, R.xml.keyboard);

        // Lookup the KeyboardView
        KeyboardView kv = (KeyboardView) findViewById(R.id.keyboardview);

        // Attach the keyboard to the view
        kv.setKeyboard(keyboard);

        // Install the key handler
        KeyboardListener keyboardListener = new KeyboardListener(this);
        kv.setOnKeyboardActionListener(keyboardListener);
        kv.setPreviewEnabled(false);
    }

    private void updateViews() {
        etInput.setText("");
        String newGuesses = String.valueOf(game.getGuesses());
        tvGuesses.setText(newGuesses);
        String displayedWord = new String(game.getDisplayedCharacters());
        tvWord.setText(displayedWord);

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

        // Update displayed letters
//        updateStringBox();

        // TO DELETE
        List<String> subset = game.getSubset();
        StringBuilder sb2 = new StringBuilder();
        for (String s : subset) {
            sb2.append(s);
            sb2.append(" ");
        }
        String subsetWords = sb2.toString();
        tvActualWord.setText(subsetWords);
    }

    /* Switch gallow image based on number of guesses left */
    private void switchImage() {
        int guesses = game.getGuesses();
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

    private void updateStringBox() {
        for (int i = 0; i < stringBox.getChildCount(); i++) {
            TextView cell = (TextView) stringBox.getChildAt(i);
            cell.setText(String.valueOf(displayedCharacters[i]));
        }
    }

    private void checkGameState() {
        // Check if the game has ended
        if (game.gameWon()) {
            showMessage(getString(R.string.game_message_won), Toast.LENGTH_LONG);

            // Hide keyboard
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            // Go to highscores
            Intent highscores = new Intent(HangmanActivity.this, HighscoresActivity.class);
            int score = game.calculateHighscore();
            highscores.putExtra("score", score);
            finish();
            startActivity(highscores);
        }

        if (game.gameLost()) {
            gameLayout.setVisibility(View.INVISIBLE);
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
        outState.putSerializable("game", this.game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.game = (Gameplay) savedInstanceState.getSerializable("game");
        updateViews();

    }

    /* Show a toast */
    private void showMessage(String message, int duration) {
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /* Let the game react to a guess of the user */
    public void nextMove(char c) {
        game.makeGuess(c);
        updateViews();
        checkGameState();
    }
}
