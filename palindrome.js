// Import readline module
const readline = require('readline');

// Create interface for input and output
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Prompt user for input
rl.question("Enter a string: ", function(input) {

    // Remove non-alphanumeric characters and convert to lowercase
    const cleanedString = input.replace(/[^a-zA-Z0-9]/g, '').toLowerCase();

    // Reverse the cleaned string
    const reversedString = cleanedString.split('').reverse().join('');

    // Check if palindrome
    if (cleanedString === reversedString) {
        console.log("The given string is a palindrome.");
    } else {
        console.log("The given string is NOT a palindrome.");
    }

    // Close the readline interface
    rl.close();
});