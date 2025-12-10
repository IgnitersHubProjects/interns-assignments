import difflib

def load_words(filename):
    with open(filename, 'r') as f:
        return [line.strip() for line in f.readlines()]

def approximate_search(words, query, k=3):
    matches = difflib.get_close_matches(query, words, n=k, cutoff=0)
    return matches

if __name__ == "__main__":
    words = load_words("words.txt")
    while True:
        query = input("Enter a word: ").strip()
        suggestions = approximate_search(words, query)
        print("Suggestions:", suggestions)