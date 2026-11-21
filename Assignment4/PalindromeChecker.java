package assignment;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        run(new Scanner(System.in));
    }

    static void run(Scanner input) {
        System.out.print("Enter a string: ");
        if (input.hasNextLine()) {
            System.out.println(check(input.nextLine()) ? "Palindrome" : "Not a palindrome");
        }
    }

    static boolean check(String text) {
        int[] letters = text.codePoints()
                .filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .toArray();

        if (letters.length == 0) {
            return false;
        }
        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            if (letters[left] != letters[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
