# Approximate word search

from pathlib import Path

WORDS_FILE = Path(__file__).resolve().parent / "words.txt"

def load_words(filename):
    words = []

    try:
        with open(filename, "r", encoding="utf-8") as file:
            for line in file:
                word = line.strip().lower()

                # skip blank lines
                if word == "":
                    continue

                words.append(word)

    except FileNotFoundError:
        print(f"Couldn't find '{filename}'.")
        print("Make sure the path is correct.")
        return []

    return words


def levenshtein_distance(word1, word2):
    # minimum number of edits (insert, delete, replace) to turn word1 into word2
    rows = len(word1) + 1
    cols = len(word2) + 1

    table = [[0] * cols for _ in range(rows)]

    # turning a word into "" takes as many deletions as it has letters
    for i in range(rows):
        table[i][0] = i

    # and building a word from "" takes as many insertions as it has letters
    for j in range(cols):
        table[0][j] = j

    for i in range(1, rows):
        for j in range(1, cols):
            if word1[i - 1] == word2[j - 1]:
                cost = 0
            else:
                cost = 1

            deletion = table[i - 1][j] + 1
            insertion = table[i][j - 1] + 1
            substitution = table[i - 1][j - 1] + cost

            table[i][j] = min(deletion, insertion, substitution)

    return table[-1][-1]


def get_suggestions(query, words, k=3):
    # how many edits away we still call "close enough"
    max_distance = max(2, len(query) // 3)

    close_words = []

    for word in words:
        # if the lengths differ by more than max_distance, the edit distance
        # can't be small enough, so skip the expensive calculation
        if abs(len(word) - len(query)) > max_distance:
            continue

        distance = levenshtein_distance(query, word)

        if distance <= max_distance:
            close_words.append((distance, word))

    # closest first
    close_words.sort(key=lambda pair: pair[0])

    # we only want the words, not the distances
    return [word for distance, word in close_words[:k]]


def main():
    print("Loading dictionary...")
    words = load_words(WORDS_FILE)

    if len(words) == 0:
        print("No words loaded, so nothing to do.")
        return

    print(f"Dictionary loaded successfully! ({len(words)} words)")
    print("\n--- Approximate Search ---")
    print("Type 'q' to quit.\n")

    while True:
        query = input("Enter a word: ").strip().lower()

        if query == "q":
            print("Goodbye!")
            break

        if query == "":
            print("Please enter a word.\n")
            continue

        suggestions = get_suggestions(query, words, k=3)

        if len(suggestions) == 0:
            print("\nNo close matches found.\n")
            continue

        print("\nSuggestions:")
        for number, word in enumerate(suggestions, start=1):
            print(f"{number}. {word}")
        print()


if __name__ == "__main__":
    main()