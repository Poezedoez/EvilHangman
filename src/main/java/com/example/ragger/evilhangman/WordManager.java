package com.example.ragger.evilhangman;

import android.content.Context;
import android.content.res.Resources;

import com.example.ragger.evilhangman.exception.NoWordsWithGivenLengthException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for loading words from a word file
 * and answering to requests of words with a given length
 *
 * @author Ragger
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

    /* Find the longest and shortest word in the word file */
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

    /* Return a random word with the given length */
    public String getRandomWordWithLength(int wordLength) throws NoWordsWithGivenLengthException {

        // accumulate a list of words with the given length
        List<String> subset = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == wordLength) {
                subset.add(words[i]);
            }
        }

        if(subset.isEmpty()) {
            throw new NoWordsWithGivenLengthException(wordLength);
        }

        // pick a random word from this list
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(subset.size());
        return subset.get(index);
    }

    /* Return a list of all words with the given word length */
    public List<String> getAllWordsWithLength(int wordLength) throws NoWordsWithGivenLengthException {
        List<String> subset = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == wordLength) {
                subset.add(words[i]);
            }
        }
        if(subset.isEmpty()) {
            throw new NoWordsWithGivenLengthException(wordLength);
        }

        return subset;
    }


    public int getShortestWordLength() {
        return this.shortestWordLength;
    }

    public int getLongestWordLength() {
        return this.longestWordLength;
    }

}
