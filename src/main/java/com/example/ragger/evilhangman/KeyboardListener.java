package com.example.ragger.evilhangman;

import android.inputmethodservice.KeyboardView;

/**
 * Created by Ragger on 3-12-2015.
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
        host.nextMove((char) primaryCode);
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
