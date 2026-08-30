package com.ayush.model;

public class Word {
    private String text;
    private double similarity;
    public Word(String text, double similarity){
        this.text = text;
        this.similarity = similarity;
    }

    public String getText() {
        return text;
    }

    public double getSimilarity() {
        return similarity;
    }
}
