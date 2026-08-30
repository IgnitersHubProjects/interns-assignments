package com.ayush.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordReader {

    final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z]+");

    public List<String> readWords(File file) throws IOException {
        ArrayList<String> wordList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
                Matcher wordmatcher = WORD_PATTERN.matcher(line);
                while (wordmatcher.find()){
                    wordList.add(wordmatcher.group());
                }

        }
        bufferedReader.close();
        return wordList;
    }
}
