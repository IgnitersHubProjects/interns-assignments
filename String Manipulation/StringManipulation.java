import java.util.*;

public class StringManipulation {

    public static void main(String[] args) {
        // takes input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();

        // string operations
        int wordCount = countWords(sentence);
        String revStr = reverseWords(sentence);
        String replacedStr = replaceSpaces(sentence);

        // Display the results
        System.out.println("Number of words: " + wordCount);
        System.out.println("Reversed sentence: " + revStr);
        System.out.println("Sentence with spaces replaced by hyphens: " + replacedStr);

        // Close the scanner
        sc.close();
    }

    private static int countWords(String str) 
    {
        if (str.trim().isEmpty())
            return 0; 
        String[] words = str.trim().split("\\s+");
        return words.length;
    }

    private static String reverseWords(String str) 
    {
        String[] words = str.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) 
        {
            reversed.append(words[i]);
            if (i != 0)
                reversed.append(" ");
        }
        return reversed.toString();
    }

    private static String replaceSpaces(String str) 
    {
        return str.replace(" ", "-");
    }
}
