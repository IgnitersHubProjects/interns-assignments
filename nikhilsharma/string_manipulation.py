def manipulate_string(sentence):
    
    words = sentence.split()
    word_count = len(words)
    reversed_sentence = ' '.join(reversed(words))
    modified_sentence = sentence.replace(' ', '-')

    print(f"Number of words: {word_count}")
    print(f"Reversed sentence: {reversed_sentence}")
    print(f"Modified sentence: {modified_sentence}")

sentence = input("Enter a sentence: ")
manipulate_string(sentence)