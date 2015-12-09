package com.example.ragger.evilhangman;

import java.util.ArrayList;
import java.util.List;

/**
 * Calling makePermutationSet({'e', '*'}, 3) returns {eee, e**, ee*, ***, etc.}
 *
 * @author Ragger
 */

public class CharacterPermutator {

    private List<String> permutations;

    /* Wrapper */
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
