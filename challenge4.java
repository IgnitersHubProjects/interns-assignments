import java.util.Scanner;

public class challenge4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

    
        String[] words = sentence.trim().split("\\s+"); 
        int wordCount = words.length;
        System.out.println("Number of words in the sentence: " + wordCount);

    
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]);
            if (i != 0) {
                reversedSentence.append(" ");
            }
        }
        System.out.println("Reversed sentence: " + reversedSentence.toString());

        
        String sentenceWithHyphens = sentence.replace(" ", "-");
        System.out.println("Sentence with spaces replaced by hyphens: " + sentenceWithHyphens);

        scanner.close();
    }
}
