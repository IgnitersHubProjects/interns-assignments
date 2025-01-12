#Input: set of N strings, k
#Output: Top k number of strings with similarity or NULL
#Constraints: Read a txt file and store in memory heap then compare

#TASK_1 APPROXIMATE SEARCH IN Python (FUZZY MATCHING)

#Using RapidFuzz based on FuzzyWuzzy (fast string matching using levenshtein distance calculation)


from rapidfuzz import process

#access file and store in list
def string_file(path):
    with open (path, "r") as file:
        content = file.read()  #one string
        lines = content.splitlines() #reading different strings by linespace
        return lines
        
        

'''
#function approx_search
:param list: content read from strings.txt in a list format
:param finder_word: finder string to find similar strings in content
:param k: top (most similar) k number of strings to return

'''

def approx_search(list, finder_word, k):
    return process.extract(finder_word, list, limit=k)



def main():

    print("Approximate Searcher App ")
    #removing whitespace using strip method
    #enter file name without the ""
    file = input("Enter the text file path: ")
    list = string_file(file)

    print(f"Loaded {len(list)} strings from the input file.")
    
    while True: #loop until exit
        finder_word = input("\nEnter a word to search (write 'exit' to quit): ").strip()
        if finder_word == "exit":
            print("Exiting app. Thanks!")
            break

        try:
            k = int(input("How many similar top k strings to view? "))
        except ValueError:               #Int error
            print("Only integers allowed.")
            continue

        answer = approx_search(list, finder_word, k)
        if answer:
            print("\nTop results:")
            for index, result in enumerate(answer, start=1):
                match, score, _ = result
                print(f"{index}. {match} (Score: {score:.2f})") #formatted strings with score upto 2 decimal places
        else:
            print("No matches found.")



if __name__ == "__main__":
    main()








