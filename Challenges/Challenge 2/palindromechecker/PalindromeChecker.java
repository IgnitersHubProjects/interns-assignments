package palindromechecker;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        if (isPalindrome(inputString)) {
            System.out.println("The string ‘"+inputString +"’ is a palindrome.");
        } else {
            System.out.println("The string ‘"+inputString +"’ is not palindrome.");
        }

        scanner.close();
    }

    private static boolean isPalindrome(String str) {
        
        str = str.replaceAll("\\s", "").toLowerCase();

        return str.equals(new StringBuilder(str).reverse().toString());
    }
}

