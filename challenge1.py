from difflib import get_close_matches

with open("words.txt", "r") as f:
    words = [line.strip() for line in f if line.strip()]

k = int(input("Enter number of suggestions (k): "))

while True:
    query = input("\nEnter word (or 'exit'): ")

    if query.lower() == "exit":
        break

    suggestions = get_close_matches(
        query,
        words,
        n=k,
        cutoff=0.4
    )

    if suggestions:
        print("Suggestions:", ", ".join(suggestions))
    else:
        print("No similar words found.")    
