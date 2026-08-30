package com.ayush;

public class PalindromeChecker {
    public static boolean check(String s){
        if (s.isEmpty()){
            return false;
        }
        s = s.trim();//remove leading spaces;
        s = s.toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
