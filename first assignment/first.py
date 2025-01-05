import numpy as np
def levenshtein_distance(str1, str2):
    len_str1 = len(str1)
    len_str2 = len(str2)
    
    dp = np.zeros((len_str1 + 1, len_str2 + 1), dtype=int)
    
    for i in range(len_str1 + 1):
        dp[i][0] = i
    for j in range(len_str2 + 1):
        dp[0][j] = j
    
    
    for i in range(1, len_str1 + 1):
        for j in range(1, len_str2 + 1):
            cost = 0 if str1[i - 1] == str2[j - 1] else 1
            dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost)
    
    return dp[len_str1][len_str2]


def load_words(filename):
    with open(filename, 'r') as file:
        words = file.read().splitlines()
    return words


def approximate_search(user_input, words, top_k=3):
    distances = []
    
    for word in words:
        distance = levenshtein_distance(user_input, word)
        distances.append((word, distance))
    
    
    distances.sort(key=lambda x: x[1])  
    return [word for word, _ in distances[:top_k]]

def main():
    
    words = load_words("word_list.txt")
    
    while True:
        
        user_input = input("Enter a word (or 'exit' to quit): ")
        
        if user_input.lower() == 'exit':
            break
        
        
        top_suggestions = approximate_search(user_input, words, top_k=3)
        
        print("Top suggestions:", ', '.join(top_suggestions))

if __name__ == "__main__":
    main()
