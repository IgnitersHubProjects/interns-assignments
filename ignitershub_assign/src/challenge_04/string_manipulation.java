package challenge;

import java.util.Scanner;

public class string_manipulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();

        // Count the number of words in the sentence
        int wordCount = countWords(input);
        System.out.println("Number of words in the sentence: " + wordCount);

        // Reverse the order of words in the sentence
        String reversedSentence = reverseWords(input);
        System.out.println("Reversed sentence: " + reversedSentence);

        // Replace all spaces with hyphens
        String hyphenatedSentence = input.replace(" ", "-");
        System.out.println("Modified sentence: " + hyphenatedSentence);
    }

    public static int countWords(String sentence) {
        String[] words = sentence.split("\\s+");
        return words.length;
    }

    public static String reverseWords(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        return reversed.toString().trim();
    }
}
