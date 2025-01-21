def is_palindrome(string):
    # Remove spaces and convert to lowercase for case-insensitive comparison
    clean_string = ''.join(string.split()).lower()
    # Check if the string is equal to its reverse
    return clean_string == clean_string[::-1]

# Prompt the user to enter a string
user_input = input("Enter a string: ")

# Check if the string is a palindrome
if is_palindrome(user_input):
    print(f"The string '{user_input}' is a palindrome.")
else:
    print(f"The string '{user_input}' is not a palindrome.")
