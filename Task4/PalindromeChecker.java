import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a String : ");
        String inputString = sc.nextLine();

        if (isPalindromeString(inputString)) {
            System.out.println("The String '" + inputString + "' is Palindrome.");
        } else {
            System.out.println("The String '" + inputString + "' is not Palindrome.");
        }
    }

    private static boolean isPalindromeString(String inputString) {    
        int startidx = 0;
        int endidx = inputString.length() - 1;
        while (startidx < endidx) {
            if (inputString.charAt(startidx) != inputString.charAt(endidx)) {
                return false;
            }
            startidx++;
            endidx--;
        }
        return true;
    }
}
