import java.util.Scanner;

class Palindrome {
    public
    Scanner input = new Scanner(System.in);
    String a, b;
    int len;

    void getStr() {
        System.out.println("Enter a string:");
        a = input.nextLine();
    }

    void calculatePalindrome() {
        b = "";
        len = a.length();
        // Reverse the string
        for (int i = len - 1; i >= 0; i--) {
            b += a.charAt(i);
        }

        // Compare the original and reversed strings
        if (a.equals(b)) {
            System.out.println("The string '" + a + "' is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
    }
}
class challenge_2{
    
    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        p.getStr();
        p.calculatePalindrome();
    }
}

