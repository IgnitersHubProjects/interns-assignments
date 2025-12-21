user_input = input("Enter a string: ")            
        #taking string input from user

reversed_string = user_input[::-1]         # slicing to go backwards

print("Reversed string:", reversed_string)          #printing the reversed string 
    
if user_input == reversed_string:
    print("The string is a palindrome.")                       #checking for palindrome
else:
    print("The string is not a palindrome.")
    print("The reversed string is:", reversed_string)

