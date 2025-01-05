# interns-assignments

# Project: Reesav Gupta - Challenges

This repository contains solutions to several challenges related to algorithms and web development. Below is an overview of each challenge implemented in this project.

## Table of Contents

1. [Challenge 1 - Approximate Search](#challenge-1-approximate-search)
2. [Challenge 2 - Evaluate Arithmetic Expressions](#challenge-2-evaluate-arithmetic-expressions)
3. [Challenge 3 - Send Email with Attachment](#challenge-3-send-email-with-attachment)
4. [Challenge 4 - Palindrome Checker](#challenge-4-palindrome-checker)

## Challenge 1 - Approximate Search

### Overview:

This program performs an approximate search using the Levenshtein distance algorithm. It takes a user input word and provides suggestions from a given text file based on the closest matches.

### Features:

- Prompts the user for a word and reads words from a file.
- Calculates the Levenshtein distance between the input word and the words in the file.
- Displays the top 3 closest words as suggestions.

### File: `approximate_search/index.js`

#### How to Run:

1. Install dependencies: `npm install prompt-sync`
2. Run the program: `node approximate_search/index.js`
3. Enter the file name and the word you want to search for.

## Challenge 2 - Evaluate Arithmetic Expressions

### Overview:

This program evaluates arithmetic expressions provided in a text file. It reads each line, evaluates the expression, and writes the result into an output file.

### Features:

- Handles implicit multiplication (e.g., `2(3 + 4)` becomes `2*(3 + 4)`).
- Supports arithmetic operators `+`, `-`, `*`, `/`, `^`, and parentheses `()`.
- Generates an output file with the original expression and its result.

### File: `evaluate_arithmetic_expression/index.js`

#### How to Run:

1. Ensure the input file (`expressions.txt`) is available.
2. Run the program: `node evaluate_arithmetic_expression/index.js`
3. The results will be written to `output.txt`.

## Challenge 3 - Send Email with Attachment

### Overview:

This program sends an email using Gmail's SMTP service with an attachment. It sends the details of the completed challenge to the specified recipient.

### Features:

- Sends an email with a customizable message.
- Attaches an image file (`attachment.jpeg`).
- Configurable email credentials using environment variables.

### Files:

- `send_email/server.js`: Express server to trigger the email sending.
- `send_email/emailService.js`: Email sending logic using Nodemailer.

#### How to Run:

1. Install dependencies: `npm install express dotenv nodemailer`
2. Set up environment variables:
   - `EMAIL`, `CLIENT_ID`, `CLIENT_SECRET`, `REFRESH_TOKEN` for Gmail OAuth2 authentication.
3. Run the server: `node send_email/server.js`
4. Visit `http://localhost:3000/send-email` to send the email.

## Challenge 4 - Palindrome Checker

### Overview:

This program checks if a given string is a palindrome or not. It reads a string from the user, reverses it, and compares it to the original string.

### Features:

- Checks for palindrome strings, ignoring leading/trailing spaces.
- Outputs whether the string is a palindrome or not.

### File: `palindrome_checker/index.js`

#### How to Run:

1. Install dependencies: `npm install prompt-sync`
2. Run the program: `node palindrome_checker/index.js`
3. Enter a string to check if it's a palindrome.

## Prerequisites

Before running any of the challenges, ensure you have Node.js installed. You can check your Node.js version by running:

```bash
node -v
```
