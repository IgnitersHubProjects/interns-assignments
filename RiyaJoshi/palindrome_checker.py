# Prompt the user to enter a string
user_input = input("Enter a string: ")

# Remove spaces and convert to lowercase for uniformity
cleaned_input = user_input.replace(" ", "").lower()

# Check if the string is the same forwards and backwards
if cleaned_input == cleaned_input[::-1]:
    print(f"The string '{user_input}' is a palindrome.")
else:
    print(f"The string '{user_input}' is not a palindrome.")
