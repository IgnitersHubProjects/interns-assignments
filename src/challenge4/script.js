function countWords(sentence) {
    let words = sentence.trim().split(/\s+/);
    return words.length;
}

function reverseWords(sentence) {
    let words = sentence.trim().split(/\s+/);
    return words.reverse().join(' ');
}

function replaceSpacesWithHyphens(sentence) {
    return sentence.replace(/\s+/g, '-');
}

let userInput = prompt("Enter a sentence:");

let wordCount = countWords(userInput);
let reversedSentence = reverseWords(userInput);
let hyphenatedSentence = replaceSpacesWithHyphens(userInput);

let ans = `
Number of words: ${wordCount}
Reversed sentence: ${reversedSentence}
Modified sentence: ${hyphenatedSentence}
`

alert(ans)