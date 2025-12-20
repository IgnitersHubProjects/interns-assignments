#take string input from user
user_input = input("Enter a string: ")
#reverse the string using slicing
reversed_string = user_input[::-1]
#print the reversed string
print("Reversed string:", reversed_string)
#check for palindrome
if user_input == reversed_string:
    print("The string is a palindrome.")
else:
    print("The string is not a palindrome.")
    print("The reversed string is:", reversed_string)

