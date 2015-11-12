package com.example.ragger.evilhangman;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ragger on 12-11-2015.
 */
public class WordChooser extends AppCompatActivity {

    String[] words;
    ArrayList<String> subset;
    int wordLength;

    public WordChooser(int wordLength){
        Resources res = getResources();
        this.wordLength = wordLength;
        this.words = res.getStringArray(R.array.words);
        subset = new ArrayList<>();
        makeInitialSubset();
    }

    private void makeInitialSubset() {
        for(int i = 0; i < words.length; i++){
            if(words[i].length() == wordLength){
                subset.add(words[i]);
            }
        }
    }

    public String getRandomWord(){
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(subset.size());
        return subset.get(index);
    }

}
