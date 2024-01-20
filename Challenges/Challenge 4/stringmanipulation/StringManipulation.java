package stringmanipulation;

import java.util.Scanner;

public class StringManipulation {

	    public static void main(String[] args) {
	        
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter a sentence: ");
	        String inputSentence = scanner.nextLine();

	        int wordCount = countWords(inputSentence);
	        System.out.println("Number of words:" + wordCount);

	        String reversedSentence = reverseWords(inputSentence);
	        System.out.println("Reversed sentence: " + reversedSentence);

	        String hyphenatedSentence = replaceSpacesWithHyphens(inputSentence);
	        System.out.println("Modified sentence: " + hyphenatedSentence);

	        scanner.close();
	    }

	    private static int countWords(String sentence) {
	        String[] words = sentence.split("\\s+");
	        return words.length;
	    }

	    private static String reverseWords(String sentence) {
	        String[] words = sentence.split("\\s+");
	        StringBuilder reversedSentence = new StringBuilder();
	        for (int i = words.length - 1; i >= 0; i--) {
	            reversedSentence.append(words[i]).append(" ");
	        }
	        return reversedSentence.toString().trim();
	    }

	    private static String replaceSpacesWithHyphens(String sentence) {
	        return sentence.replaceAll("\\s", "-");
	    }
	}
