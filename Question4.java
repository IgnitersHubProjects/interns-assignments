package ignitershub.assignment;

import java.util.Scanner;

public class Question4 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter a string: ");
			String input = scanner.nextLine();

			if (isPalindrome(input)) {
			    System.out.println("The string '" + input + "' is a palindrome.");
			} else {
			    System.out.println("The string" + input + "' is not a palindrome.");
			}
		}
    }

    private static boolean isPalindrome(String str) {
        
        String cleanStr = str.replaceAll("[^a-zA-Z]", "").toLowerCase();

        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

