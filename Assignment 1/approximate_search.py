'''Program: Write an interactive program to implement ‘Approximate Search’ for a given set of N
strings. That is, given a word, find the top k strings in the set among those that are
similar.
Example:1
Input >> abslutly
Ouput >> absolutely

Example:2
Input >> actv
Ouput >> active, activist, activity
'''

from difflib import SequenceMatcher

# Read words from file
with open("words.txt", "r") as file:
    words = []
    for line in file:
        words.append(line.strip())

print("**********************APPROXIMATE SEARCH****************************")

while True:
    search_word = input("\nEnter a word (or type exit): ")
    if search_word.lower() == "exit":
        break
    result = []
    for word in words:
        score = SequenceMatcher(None, search_word.lower(), word.lower()).ratio()
        if score >= 0.5:
            result.append((word, score))
    result.sort(key=lambda x: x[1], reverse=True)
    for word, score in result:
        print(word)


