/*
Challenge 3 – Palindrome Checker

Write a program that checks whether a given string is a palindrome or not. A
palindrome is a word, phrase, or sequence of characters that reads the same
backwards as forward.
*/

import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String str = sc.next();
        String org_str = str;

        String rev ="";
        int len = str.length();

        for(int i = len-1; i>=0; i--){
            rev = rev + str.charAt(i);
        }
        if(org_str.equals(rev)){
            System.out.println(org_str+" is a Palindrome String");
        }else{
            System.out.println(org_str+" is not a Palindrome String");
        }
    }
}
