#task 4 palindrome checker

def palindrome(string):


    if string  == string[::-1]: #slicing with start end full and -1 is reverse step
        print("String is a palindrome")
        return 1
    else:
        print("string is not a palindrome")
        return 0

    

def main():

    while True:
        s = input("Enter string: (or write 'x' to exit) ")
        if s =='x':
            print("exiting loop, thanks for using my app!")
            break
        palindrome(s)

if __name__ == "__main__":
    main()