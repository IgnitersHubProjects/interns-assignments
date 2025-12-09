import difflib
import os

# Ask user for file path
file_path = input("Enter the path to your words file (or press Enter to use default): ").strip()

# If user presses Enter, use default file
if not file_path:
    file_path = r"C:\Users\Riya Joshi\OneDrive\Desktop\IgnitersHub\words.txt"

# Check if file exists
if not os.path.isfile(file_path):
    print(f"File '{file_path}' not found. Creating a sample word list...")
    sample_words = ["absolutely", "active", "activist", "activity", "apple", "banana", "orange"]
    words = sample_words
else:
    with open(file_path, "r") as f:
        words = f.read().split()  # Reads all words (space or newline separated)

print("Approximate Search Ready! Type 'exit' to quit.")

while True:
    query = input("Input >> ").strip()
    if query.lower() == "exit":
        print("Exiting program.")
        break
    # Get top 3 matches
    matches = difflib.get_close_matches(query, words, n=3, cutoff=0.5)
    print("Output >>", ", ".join(matches) if matches else "No suggestions found")
