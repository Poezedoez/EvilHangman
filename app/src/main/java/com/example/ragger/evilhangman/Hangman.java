package com.example.ragger.evilhangman;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Ragger on 17-11-2015.
 */
public class Hangman extends AppCompatActivity {

    private WordLoader wordLoader;
    private Settings settings;
    private Gameplay game;
    private TextView tvGuesses, tvGuessedCharacters, tvWord;
    private EditText etInput;
    private ImageView ivHangman;
    private Button bGuess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        loadPrefs(this);
        loadWords(this);
        loadViews();
        loadGame();
        initializeViews();
    }

    private void initializeViews() {
        tvGuesses.setText(String.valueOf(settings.getInitialGuesses()));
        bGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char guessedCharacter = etInput.getText().charAt(0);
                game.makeGuess(guessedCharacter);
                updateViews();
                checkGameState();
            }
        });
        String displayedWord = new String(game.getDisplayedCharacters());
        tvWord.setText(displayedWord);
    }

    private void checkGameState() {
        if (game.gameWon()) {
            Toast.makeText(getApplicationContext(), "Well done!!! :^)",
                    Toast.LENGTH_LONG).show();
        }
        if (game.gameLost()) {
            Toast.makeText(getApplicationContext(), "You lost '_|'",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void updateViews() {
        String newGuesses = String.valueOf(game.getGuesses());
        tvGuesses.setText(newGuesses);
        String displayedWord = new String(game.getDisplayedCharacters());
        tvWord.setText(displayedWord);

        // code to update guessed characters
        StringBuilder sb = new StringBuilder();
        List<Character> guessedCharacters = game.getGuessedCharacters();
        for (Character c  : guessedCharacters) {
            sb.append(c);
            sb.append("; ");
        }
        String charString = sb.toString();
        tvGuessedCharacters.setText(charString);
    }

    private void loadViews() {
        this.tvGuesses = (TextView) findViewById(R.id.tvGuesses);
        this.tvGuessedCharacters = (TextView) findViewById(R.id.tvGuessedCharacters);
        this.tvWord = (TextView) findViewById(R.id.tvWord);
        this.etInput = (EditText) findViewById(R.id.etInput);
        this.ivHangman = (ImageView) findViewById(R.id.ivHangman);
        this.bGuess = (Button) findViewById(R.id.bGuess);
    }

    private void loadWords(Context context) {
        this.wordLoader = new WordLoader(context);
    }

    private void loadPrefs(Context context) {
        this.settings = new Settings(context);
    }

    private void loadGame() {
        boolean evilModeEnabled = settings.getMode();
        if(evilModeEnabled) {
            this.game = new EvilGameplay(settings, wordLoader);
        } else {
            this.game = new GoodGameplay(settings, wordLoader);
        }
    }


}
