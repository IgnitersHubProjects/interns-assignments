import java.util.Scanner;

public class StringManipulation {
    
    static String replaceSpace(String str) {
        String s = "";

        for (int i = 0; i < str.length(); ++i) {

            if (str.charAt(i) == ' ') 
                s += '-';
            else
                s += str.charAt(i);
        }
        return s;
    }
    

    static int countWords(String str) {

        int total = 1;
        int i = 0;

        while (i < str.length()) {
            if (str.charAt(i) == ' ' && i + 1 < str.length() && str.charAt(i + 1) != ' ') {
                total++;
            }
            i++;
        }
        
        return total;
    }
    

    static String reverseWords(String str) {

        char[] arr = str.toCharArray();
        
        int j = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == ' ') {
                reverse(arr, j, i - 1);
                j = i + 1;
            }
        }
        
        reverse(arr, j, arr.length - 1);
        
        reverse(arr, 0, arr.length - 1);
        
        return new String(arr);
    }
    
    static void reverse(char[] arr, int j, int i) {
        while (j < i) {
            char temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j++;
            i--;
        }
    }

    public static void main(String []args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence: ");
        String inputSentence = scanner.nextLine();
        
        scanner.close();
        
        String modifiedSentence = replaceSpace(inputSentence);
        
        int wordCount = countWords(inputSentence);
        
        String reversedSentence = reverseWords(inputSentence);
        
        System.out.println("Number of words: " + wordCount);
        System.out.println("Reversed sentence: " + reversedSentence);
        System.out.println("Modified sentence: " + modifiedSentence);
    }
}
