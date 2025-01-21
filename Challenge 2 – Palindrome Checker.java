import java.util.Scanner;

class PalindromeChecker {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Check if the string is a palindrome
        if (isPalindrome(input)) {
            System.out.println("The string '" + input + "' is a palindrome.");
        } else {
            System.out.println("The string '" + input + "' is not a palindrome.");
        }

        // Close the scanner
        scanner.close();
    }

    // Method to check if a string is a palindrome using the two-pointer approach
    public static boolean isPalindrome(String str) {
        // Remove spaces, convert to lowercase, and remove non-alphanumeric characters
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Initialize two pointers: one at the start and one at the end of the string
        int left = 0;
        int right = cleanStr.length() - 1;

        // Move the pointers toward the center
        while (left < right) {
            // Compare characters at the two pointers
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false; // Not a palindrome
            }
            left++; // Move the left pointer forward
            right--; // Move the right pointer backward
        }

        return true; // It's a palindrome
    }
}
