package com.example.ragger.evilhangman;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutation class that consists of modified code
 * from Abhinav Ramana http://www.geeksforgeeks.org/print-all-combinations-of-given-length/
 * calling makePermutationSet({'e', '*'}, 3) returns {eee, e**, ee*, ***, etc.}
 * Created by Ragger on 1-12-2015.
 */

public class CharacterPermutator {

    List<String> permutations;

    /* Wrapper method that makes a permutation set by calling the private method */
    public void makePermutationSet(char[] set, int k) {
        int n = set.length;
        this.permutations =  new ArrayList<>();
        makePermutationSet(set, "", n, k);
    }

    /* Make all possible permutations with given characters and a permutation length */
    private void makePermutationSet(char[] set, String prefix, int n, int k) {

        // Base case: if k is 0, add permutation
        if (k == 0) {
            permutations.add(prefix);
            return;
        }

        // Recursively make permutations with given characters
        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            makePermutationSet(set, newPrefix, n, k - 1);
        }
    }

    public List<String> getPermutations(){
        return this.permutations;
    }

}
