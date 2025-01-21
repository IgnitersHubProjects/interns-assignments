import Levenshtein


def read_file(file_path):
    """Read a file and store each line as a string in a list."""
    with open(file_path, 'r') as file:
        strings = [line.strip() for line in file.readlines()]
    return strings


def contains_characters_in_sequence(word, candidate):
    """Check if the characters of word appear in sequence in candidate."""
    word = word.lower()
    candidate = candidate.lower()

    i = 0
    for char in candidate:
        if char == word[i]:
            i += 1
        if i == len(word):
            return True
    return False


def approximate_search(word, string_set, k=3):
    """Find the top k strings from the set that are most similar to the input word."""
    word_lower = word.lower()

    # Filter words that contain the characters of the word in sequence
    filtered_strings = [s for s in string_set if contains_characters_in_sequence(word_lower, s)]

    if not filtered_strings:
        return ["No results found"]

    # Calculate the Levenshtein distance for the filtered strings
    distances = [(s, Levenshtein.distance(word_lower, s.lower())) for s in filtered_strings]
    sorted_distances = sorted(distances, key=lambda x: x[1])

    return [s for s, dist in sorted_distances[:k]]


def interactive_search(file_path, k=3):
    """Interactive search function that reads the file and allows the user to input words for searching."""
    # Read the text file and parse its content into a list
    string_set = read_file(file_path)
    print("Approximate Search - Enter a word to get suggestions:")

    while True:
        # Prompt the user to input a word
        word = input("\nEnter a word (or 'exit' to quit): ").strip()

        if word.lower() == 'exit':
            break

        # Find the top k similar strings
        suggestions = approximate_search(word, string_set, k)
        print(f"Suggestions: {', '.join(suggestions)}")


# Example usage
if __name__ == "__main__":
    file_path = "words.txt"  # Replace with your file path
    interactive_search(file_path, k=3)
