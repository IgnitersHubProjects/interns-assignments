# Igniters Hub | Java Set 1

Open a terminal in this folder and run:

```text
java -jar target/assignments.jar
```

The JAR packages the compiled Java classes and their libraries. On a fresh copy, use Java 17 or newer and run `mvn package` once to create it.

| Option | What it does | Run directly |
| --- | --- | --- |
| 1 | Suggests the three closest words from `Assignment1/words.txt`. Try `actv`. A blank line quits. | `java Assignment1/ApproximateSearch.java` |
| 2 | Solves `Assignment2/expressions.txt` and writes `Assignment2/answers.txt`. | `java Assignment2/ArithmeticExpressions.java` |
| 3 | Sends the challenge email with a PNG or JPEG attachment. | Pick option 3 or use the command below. |
| 4 | Checks whether a word or phrase is a palindrome. Try `racecar`. | `java Assignment4/PalindromeChecker.java` |

Use `java Assignment1/ApproximateSearch.java my-words.txt 5` for a custom dictionary and result count. Search ranks words by case-insensitive edit distance, with ties in file order. The menu version loads the dictionary through Hibernate into an in-memory H2 database.

Use `java Assignment2/ArithmeticExpressions.java input.txt output.txt` for custom files. The calculator handles brackets, powers and operator precedence. Malformed lines get an error in the output. Exponents range from -1000 to 1000 and fractional powers are approximated.

## Test Assignment 3 with Gmail

1. Enable 2-Step Verification on the **sending** Google account. Create an [App Password](https://myaccount.google.com/apppasswords) for this program.
2. From a real terminal, run the command below with your image path, sender address and test recipient address, in that order.
3. Enter your name, semester, branch and roll number. Paste the app password at the hidden prompt.
4. Check the recipient inbox for **Challenge 3 Completed** and open the attachment.

```text
java -jar target/assignments.jar email "/path/to/photo.png" "sender@gmail.com" "recipient@gmail.com"
```

The program defaults to Gmail SMTP on port 587 with STARTTLS. Enter the app password at the prompt so it stays out of the repository and shell history. After your personal test succeeds, run the same command with `hr@ignitershub.com` as the recipient for the assignment.
