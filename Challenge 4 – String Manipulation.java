import java.util.Scanner;
class SentenceOperations {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a sentence
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        // Perform operations
        int wordCount = countWords(sentence);
        String reversedSentence = reverseWords(sentence);
        String modifiedSentence = replaceSpacesWithHyphens(sentence);

        // Display the results
        System.out.println("Number of words: " + wordCount);
        System.out.println("Reversed sentence: " + reversedSentence);
        System.out.println("Modified sentence: " + modifiedSentence);

        // Close the scanner
        scanner.close();
    }

    // Method to count the number of words in a sentence
    public static int countWords(String sentence) {
        // Split the sentence into words using whitespace as the delimiter
        String[] words = sentence.trim().split("\\s+");
        return words.length;
    }

    // Method to reverse the order of words in a sentence
    public static String reverseWords(String sentence) {
        // Split the sentence into words
        String[] words = sentence.trim().split("\\s+");

        // Reverse the words using a StringBuilder
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }

        return reversed.toString();
    }

    // Method to replace all spaces with hyphens
    public static String replaceSpacesWithHyphens(String sentence) {
        return sentence.replace(" ", "-");
    }
}

