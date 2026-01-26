def is_palindrome(s: str) -> bool:
    return s == s[::-1]


def main():
    print("===== Challenge 4: Palindrome Checker =====")
    s = input("Enter a string : ").strip()

    if is_palindrome(s):
        print(f"The string '{s}' is a palindrome.")
    else:
        print(f"The string '{s}' is not a palindrome.")


if __name__ == "__main__":
    main()
