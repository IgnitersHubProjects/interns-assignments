import java.util.Scanner;

public class SentenceOperations {
    public static void main(String[] args) {
        // Take input sentence from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String inputSentence = scanner.nextLine();
        
        // Perform operations
        int wordCount = countWords(inputSentence);
        String reversedSentence = reverseWords(inputSentence);
        String hyphenatedSentence = replaceSpacesWithHyphens(inputSentence);
        
        // Display results
        System.out.println("1. Number of words: " + wordCount);
        System.out.println("2. Reversed sentence: " + reversedSentence);
        System.out.println("3. Hyphenated sentence: " + hyphenatedSentence);
        
        scanner.close();
    }

    // Function to count the number of words in a sentence
    private static int countWords(String sentence) {
        String[] words = sentence.split("\\s+");
        return words.length;
    }

    // Function to reverse the order of words in a sentence
    private static String reverseWords(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        return reversed.toString().trim();
    }

    // Function to replace spaces with hyphens in a sentence
    private static String replaceSpacesWithHyphens(String sentence) {
        return sentence.replace(" ", "-");
    }
}
