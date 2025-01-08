import java.util.Scanner;

class StringManipulation {
    String str, hyphenated;
    int count;
    StringManipulation() {  // Constructor
        count = 1;
    }
        void getStr() {    // Method to get the string
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the sentence:");
        str = input.nextLine();
        hyphenated = str; // Copy the string for later manipulation
    }
    void strCount() {
        int len = str.length();
        count = 1; 
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }
        System.out.println("Number of words: " + count);
    }

 
    void strReverse() {    // Method to reverse the sentence
        String[] words = str.split(" ");
        StringBuilder reversedSentence = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }

        System.out.println("Reversed sentence: " + reversedSentence.toString().trim());
    }

    
    void getHyphens() {  // Method to replace spaces with hyphens
        hyphenated = hyphenated.replace(" ", "-");
        System.out.println("Modified sentence: " + hyphenated);
    }
}

public class challenge_4 {
    public static void main(String[] args) {
        StringManipulation sm = new StringManipulation();
        sm.getStr();
        sm.strCount();
        sm.strReverse();
        sm.getHyphens();
    }
}
