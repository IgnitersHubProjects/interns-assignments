package com.palin.Palindrome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/palindrome")
public class PalindromeCheckerSpring {

    @GetMapping("/check")
    public String checkPalindrome(@RequestParam String input) {
        String str = input.toLowerCase();
        String rev = new StringBuilder(str).reverse().toString();

        if (str.equals(rev))
            return "The string \"" + input + "\" is a palindrome.";
        else
            return "The string \"" + input + "\" is not a palindrome.";
    }
}
