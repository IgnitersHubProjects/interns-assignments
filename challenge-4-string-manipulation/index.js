const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter a sentence: ", function (sentence) {

    // Count words
    const words = sentence.trim().split(/\s+/);
    const wordCount = sentence.trim() === "" ? 0 : words.length;

    // Reverse word order
    const reversed = [...words].reverse().join(" ");

    // Replace spaces with hyphens
    const hyphenated = sentence.trim().replace(/\s+/g, "-");

    console.log("Number of words:", wordCount);
    console.log("Reversed sentence:", reversed);
    console.log("Sentence with hyphens:", hyphenated);

    rl.close();
});