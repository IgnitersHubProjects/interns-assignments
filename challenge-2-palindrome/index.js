const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter a string: ", function (input) {

    const reversed = input.split("").reverse().join("");

    if (input.toLowerCase() === reversed.toLowerCase()) {
        console.log("It is a palindrome.");
    } else {
        console.log("It is not a palindrome.");
    }

    rl.close();
});