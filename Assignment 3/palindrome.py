'''Write a program that checks whether a given string is a palindrome or not. A
palindrome is a word, phrase, or sequence of characters that reads the same
backwards as forward.
Input
Enter a string : racecar

Output
The string ‘racecar’ is a palindrome.'''

word = input("Enter a string: ")
reverse_word = word[::-1]
if word == reverse_word:
    print("The string '" + word + "' is a palindrome.")
else:
    print("The string '" + word + "' is not a palindrome.")
