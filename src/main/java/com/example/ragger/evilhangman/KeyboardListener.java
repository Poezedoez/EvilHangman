package com.example.ragger.evilhangman;

import android.inputmethodservice.KeyboardView;

import com.example.ragger.evilhangman.exception.IllegalGuessException;

/**
 * Keyboard listener defined in seperate class for the sake of readability
 *
 * @author Ragger
 */
public class KeyboardListener implements KeyboardView.OnKeyboardActionListener {

    HangmanActivity host;

    public KeyboardListener(HangmanActivity host){
        this.host = host;
    }
    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        try {
            host.guess((char) primaryCode);
        } catch (IllegalGuessException e) {
            // Do nothing; keyboard warrants character range
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
