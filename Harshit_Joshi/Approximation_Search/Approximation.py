import re
import heapq
from typing import List, Tuple


def levenshtein_distance(a: str, b: str) -> int:
    """
    Computes Levenshtein distance between strings a and b.
    Time: O(len(a)*len(b)), Space: O(min(len(a), len(b))) using 2-row DP.
    """
    a = a.lower()
    b = b.lower()

    if a == b:
        return 0
    if not a:
        return len(b)
    if not b:
        return len(a)

    # Ensure b is smaller for memory efficiency
    if len(b) > len(a):
        a, b = b, a

    prev = list(range(len(b) + 1))

    for i, ca in enumerate(a, start=1):
        curr = [i]
        for j, cb in enumerate(b, start=1):
            cost = 0 if ca == cb else 1
            curr.append(min(
                prev[j] + 1,      # deletion
                curr[j - 1] + 1,  # insertion
                prev[j - 1] + cost  # substitution
            ))
        prev = curr

    return prev[-1]


def load_unique_words(filename: str) -> List[str]:
    """Loads words from file, returns unique lowercase words."""
    with open(filename, "r", encoding="utf-8") as f:
        text = f.read()

    words = re.findall(r"[A-Za-z]+", text)
    unique_words = sorted(set(w.lower() for w in words))
    return unique_words


def approximate_search(query: str, words: List[str], k: int) -> List[str]:
    """
    Returns top-k closest matches using Levenshtein distance.
    Tie-breaker: lexicographical order.
    """
    query = query.strip().lower()
    if not query:
        return []

    # Use heap to efficiently pick smallest distances
    heap: List[Tuple[int, str]] = []
    for w in words:
        d = levenshtein_distance(query, w)
        heapq.heappush(heap, (d, w))

    results = [heapq.heappop(heap)[1] for _ in range(min(k, len(heap)))]
    return results


def main():
    print("===== Challenge 1: Approximate Search =====")
    filename = input("Enter text filename (example: sample_words.txt): ").strip()

    try:
        words = load_unique_words(filename)
        print(f"✅ Loaded {len(words)} unique words into memory.\n")
    except FileNotFoundError:
        print("❌ File not found. Please check file name/path.")
        return

    while True:
        try:
            k = int(input("Enter k (number of suggestions): ").strip())
            if k <= 0:
                print("❌ k must be a positive integer.")
                continue
            break
        except ValueError:
            print("❌ Please enter a valid integer for k.")

    print("\nType a word to search (type 'exit' to quit)")
    while True:
        query = input("Input >> ").strip()
        if query.lower() == "exit":
            print("Exiting...")
            break

        suggestions = approximate_search(query, words, k)
        if suggestions:
            print("Output >> " + ", ".join(suggestions))
        else:
            print("Output >> No suggestions found.")


if __name__ == "__main__":
    main()
