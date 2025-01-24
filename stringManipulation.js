function String(string) {
    const wordCount = string.trim().split(/\s+/).length;
    const reverseString = string.trim().split(/\s+/).reverse().join(' ');
    const modifiedString = string.replace(/\s+/g, '-');

    return {
        wordCount: wordCount,
        reverseString: reverseString,
        modifiedString: modifiedString
    };
}

const string = "Igniters Hub";
const result = String(string);

console.log("Word Count:", result.wordCount);
console.log("Reversed String:", result.reverseString);
console.log("Modified String:", result.modifiedString);
