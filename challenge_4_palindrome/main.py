def palindrome(word):
    #lowercase ir and remove spaces 
    cleaned = "".join(ch.lower() for ch in word if ch.isalnum())

    # compare the string with its reverse
    return cleaned == cleaned[::-1]

def main():
    print("-:palindrome cheaker:-")
    print("(type 'q' to quit)\n")

    while True:
        word = input("Enter a word or phrase:").strip()

        if word.lower() == "q":
            print("Goodbye!")
            break

        if word == "":
            print("typeing something?, try agian.\n")
            continue

        if palindrome(word):
            print(f"yes, '{word}' is palindrome! \n")
        else:
            print(f"Nope, '{word}' os not a palindrome.\n")

if __name__ == "__main__":
   main()               