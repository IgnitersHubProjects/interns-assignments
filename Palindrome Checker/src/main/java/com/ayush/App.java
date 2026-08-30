package com.ayush;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\nEnter a String : ");
        String input = scanner.nextLine();
        if (input.isEmpty()){
            System.out.printf("\nempty string\n");
            return;
        }

        boolean isPalindrome = PalindromeChecker.check(input);
        if(isPalindrome){
            System.out.printf("\nThe string %s is a palindrome\n", input);
        }
        else {
            System.out.printf("\nThe string %s is not a palindrome\n", input);
        }
        scanner.close();
    }
}
