
# AUTHOR:- SHUBHAM CHAUDHARY

# CHALLANGE 1 ANSWER







# CHALLANGE 2 ANSWER

def is_palindrome(s):
    string1 = ''.join(s.split()).lower()

    # Check if the cleaned string is equal to its reverse
    return string1 == string1[::-1]


# Prompt the user to enter a string
user_input = input("Enter a string: ")

# Check if the entered string is a palindrome
if is_palindrome(user_input):
    print(f"The string '{user_input}' is a palindrome.")
else:
    print(f"The string '{user_input}' is not a palindrome.")


# CHALLANGE 3 ANSWER


def binary_search(arr, target):
    low, high = 0, len(arr) - 1

    while low <= high:
        mid = (low + high) // 2
        mid_element = arr[mid]

        if mid_element == target:
            return mid
        elif mid_element < target:
            low = mid + 1
        else:
            high = mid - 1

    return -1


target_element = int(input("Enter the target element: "))


sorted_array = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]


result = binary_search(sorted_array, target_element)

if result != -1:
    print(f"Element {target_element} is found at index {result}.")
else:
    print(f"Element {target_element} is not in the array.")


# CHALLANGE 4 ANSWER

def process_sentence(sentence):

    word_count = len(sentence.split())

    reversed_sentence = ' '.join(reversed(sentence.split()))

    modified_sentence = sentence.replace(' ', '-')

    return word_count, reversed_sentence, modified_sentence


user_input = input("Enter a sentence: ")


count, reversed_sentence, modified_sentence = process_sentence(user_input)


print(f"Number of words: {count}")
print(f"Reversed sentence: {reversed_sentence}")
print(f"Modified sentence: {modified_sentence}")
