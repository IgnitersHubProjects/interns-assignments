def is_palindrome(text):
    cleaned = ''.join(ch.lower() for ch in text if ch.isalnum())
    return cleaned == cleaned[::-1]

text = input("Enter a string: ")
if is_palindrome(text):
    print(f"The string '{text}' is a palindrome.")
else:
    print(f"The string '{text}' is not a palindrome.")