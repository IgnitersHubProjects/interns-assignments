def is_palindrome(s):
    s = s.lower().replace(" ", "") 
    return s == s[::-1]

string = input("Enter a string: ")

if is_palindrome(string):
    print(f"The string '{string}' is a palindrome.")
else:
    print(f"The string '{string}' is not a palindrome.")