import difflib   
# approximate string matching library
# reading words from file
with open(r"D:\git-work\interns-assignments\Vaibhav_Shukla\words.txt", "r") as file:
    words = [line.strip() for line in file]

print("Approximate Search using k similar strings")
print("Type 'exit' to stop")

while True:
    user_input = input("Input >> ").strip() 
    if user_input == "exit":
        break

    k = int(input("Enter k >> "))     

    matches = difflib.get_close_matches(
        user_input, words, n=k, cutoff=0.6
    )  # finding k similar words with at least 60% similarity  

    if matches:
        print("Output >>", ", ".join(matches))
    else:
        print("Output >> No similar words found")
