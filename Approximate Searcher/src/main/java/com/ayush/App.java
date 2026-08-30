package com.ayush;

import com.ayush.service.WordSearcher;
import com.ayush.util.WordReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class App 
{
    public static void main( String[] args ) throws IOException {
        //parse input text file

        File file = new File("Approximate Searcher/data/input.txt");
        WordReader wordReader = new WordReader();
        List<String> candidateWords = wordReader.readWords(file);


        //fetch input from user
        Scanner scanner = new Scanner(System.in);

        System.out.printf("\nenter text to search : ");
        String searchTerm = scanner.nextLine();
        System.out.printf("\nenter desired top words count : ");
        int topWordsCount = Integer.parseInt(scanner.nextLine());

        scanner.close();

        //find top k matching words
        ArrayList<String> topKMatchingWords = WordSearcher.search(candidateWords, searchTerm, topWordsCount );
        System.out.printf("\ntop k matching words\n");
        for (String word : topKMatchingWords){
            System.out.printf("\n%s\n", word);
        }


    }
}
