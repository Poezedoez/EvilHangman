package com.example.ragger.evilhangman;


import com.example.ragger.evilhangman.exception.IllegalGuessException;
import com.example.ragger.evilhangman.exception.NoWordsWithGivenLengthException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is instantiated when the user decides to play the game on evil mode (default)
 * Evil gameplay uses a different way of choosing a word than standard Hangman.
 *
 * @author Ragger
 */

public class EvilGameplay extends AbstractGameplay {

    private List<String> subset;

    public EvilGameplay(SettingsManager settingsManager, WordManager wordManager) throws NoWordsWithGivenLengthException {
        super(settingsManager);
        this.subset = wordManager.getAllWordsWithLength(wordLength);
    }

    @Override
    public void guess(char input) throws IllegalGuessException {

        // If last word in set, no need to look for other subsets
        if(isFinalWord()) {
            super.guess(input);
            return;
        }

        // Get new subset
        subset = getNewSubset(input);
        word = subset.get(0);

        super.guess(input);
    }

    @Override
    public int getScore() {
        return super.getScore() * 2;
    }

    /* Returns a new subset while trying to avoid words with the user input */
    private List<String> getNewSubset(char c) {

        // Get all possible permutations with the guessed character
        CharacterPermutator cp = new CharacterPermutator();
        char[] set = {c, '*'};
        cp.makePermutationSet(set, wordLength);
        List<String> permutations = cp.getPermutations();

        // Divide the current subset into equality lists
        List<List<String>> listsWithDifferentEquality = new ArrayList<>();

        // Check for every permutation if the words have the same equality
        for(String permutation : permutations){
            List<String> list = new ArrayList<>();
            int characterOccurrences = countOccurrences(permutation, c);
            for(String word : subset){
                int permutationEquality = 0;
                int totalCount = 0;
                for(int i = 0; i < wordLength; i++){
                    if(word.charAt(i) == permutation.charAt(i)){
                        permutationEquality++;
                    }
                    if(word.charAt(i) == c){
                        totalCount++;
                    }
                }
                // If the number and positions of the characters correspond,
                // add them to the list for that permutation
                if(permutationEquality == characterOccurrences && characterOccurrences == totalCount){
                    list.add(word);
                }
            }
            listsWithDifferentEquality.add(list);

        }
        return getLargestStringList(listsWithDifferentEquality);
    }

    /* Count the occurences of character c in string s */
    private int countOccurrences(String s, char c) {
        int occurrences = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == c){
                occurrences++;
            }
        }
        return occurrences;
    }

    private boolean isFinalWord() {
        return subset.size() == 1;
    }

    /* Return the largest list of strings and break ties randomly */
    private List<String> getLargestStringList(List<List<String>> listOfLists) {

        // Find max list length
        int maxListLength = 0;
        for (List<String> list : listOfLists) {
            if (list.size() > maxListLength) {
                maxListLength = list.size();
            }
        }

        // Get all the lists with this length
        List<List<String>> maxLists = new ArrayList<>();
        for (List<String> list : listOfLists) {
            if (list.size() == maxListLength) {
                maxLists.add(list);
            }
        }

        // Randomly return one of these max lists
        Random ran = new Random();
        int luckyList = ran.nextInt(maxLists.size());
        return maxLists.get(luckyList);
    }

}
