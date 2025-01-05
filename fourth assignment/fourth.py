def is_palindrome(string):
    string = string.replace(" ", "").lower()
    return string == string[::-1]

user_input = input("Enter a string: ")
if is_palindrome(user_input):
    print(f"The string '{user_input}' is a palindrome.")
else:
    print(f"The string '{user_input}' is not a palindrome.")
