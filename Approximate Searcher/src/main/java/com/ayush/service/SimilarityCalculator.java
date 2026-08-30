package com.ayush.service;

public class SimilarityCalculator {
    public double calculate(String word, String key){
        //number of operations required to convert given word to key
        double editDistance = calculateEditDistance(word, key);
        //normalise
        return 1 - (editDistance / Math.max(key.length(), word.length()));
    }
    private double calculateEditDistance(String word, String key){
        //calculate Levenshtein distance
        int rows = key.length() + 1, cols = word.length()+1;
        int[][] DP = new int[rows][cols]; // distance matrix
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols; j++){
                if(i == 0){
                    DP[i][j] = j;
                }
                else if (j == 0){
                    DP[i][j] = i;
                }
                else{
                    DP[i][j] = min(
                            DP[i-1][j] + 1, //deletion
                            DP[i][j-1] + 1,     //insertion
                            DP[i-1][j-1] + ((key.charAt(i-1) == word.charAt(j-1)) ? 0 : 1) //substitution
                    );
                }
            }
        }
        return DP[rows-1][cols-1];
    }
    private int min(int a, int b, int c){
        return Math.min(a, Math.min(b,c));
    }
}
