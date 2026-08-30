package com.ayush.service;

import com.ayush.model.Word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class WordSearcher {
    public static ArrayList<String> search(List<String> candidateWords, String searchTerm ,int topWordCount){

        PriorityQueue<Word> maxHeap = new PriorityQueue<>(
                Comparator.comparing(( Word word) -> word.getSimilarity()).reversed()

        );

        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        for (String word : candidateWords){
            Word wordObj = new Word(word, similarityCalculator.calculate(word, searchTerm));
            maxHeap.add(wordObj);
        }

        ArrayList<String> matchingWords = new ArrayList<>();
        while (!maxHeap.isEmpty() && topWordCount > 0){
            Word word = maxHeap.poll();
            matchingWords.add(word.getText());
            topWordCount--;
        }
        return matchingWords;
    }


}
