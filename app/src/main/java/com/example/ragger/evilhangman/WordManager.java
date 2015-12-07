package com.example.ragger.evilhangman;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ragger on 12-11-2015.
 * This class is responsible for loading words from a word file
 * and answering to requests of words with a given length
 */
public class WordManager {

    private String[] words;
    private int shortestWordLength;
    private int longestWordLength;

    public WordManager(Context context) {
        Resources res = context.getResources();
        this.words = res.getStringArray(R.array.test_words);
        setWordLengths();
    }
    
    public String getRandomWordWithLength(int wordLength){
        List<String> subset = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == wordLength) {
                subset.add(words[i]);
            }
        }
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(subset.size());
        return subset.get(index);
    }

    public List<String> getAllWordsWithLength(int wordLength) {
        List<String> subset = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == wordLength) {
                subset.add(words[i]);
            }
        }
        return subset;
    }


    public int getShortestWordLength() {
        return this.shortestWordLength;
    }

    public int getLongestWordLength() {
        return this.longestWordLength;
    }

    private void setWordLengths() {
        this.shortestWordLength = words[0].length();
        this.longestWordLength = words[0].length();
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() < shortestWordLength) {
                this.shortestWordLength = words[i].length();
            }
            if (words[i].length() > longestWordLength) {
                this.longestWordLength = words[i].length();
            }
        }
    }
}
