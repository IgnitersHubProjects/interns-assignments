//Palindrome Code
import java.util.*;

public class PalindromeChecker {

    public static void main(String[] args) 
    {
        // takes input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check if it's a palindrome: ");
        String input = sc.nextLine();

        // checking method
        boolean isPalindrome = checkPalin(input);

        // display result
        if (isPalindrome)
            System.out.println("The string '" + input + "' is a palindrome.");
        else
            System.out.println("The string '" + input + "' is not a palindrome.");

        sc.close();
    }

    private static boolean checkPalin(String input) {
        // Remove non-alphanumeric characters and convert to lowercase
        String str = input.toLowerCase();

        // Reverse the string
        String reversed = new StringBuilder(str).reverse().toString();

        // Compare the original and reversed strings
        return str.equals(reversed);
    }
}
