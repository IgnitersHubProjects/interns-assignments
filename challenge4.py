text = input("Enter a string: ")

if text == text[::-1]:
    print(f"The string '{text}' is a palindrome.")
else:
    print(f"The string '{text}' is not a palindrome.")