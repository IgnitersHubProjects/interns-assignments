const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function isPalindrome(str) {
    let cleanedStr = str.replace(/[^A-Za-z0-9]/g, '').toLowerCase();

    let reversedStr = cleanedStr.split('').reverse().join('');
    
    return cleanedStr === reversedStr;
}

rl.question('Enter a string: ', (userInput) => {
    if (isPalindrome(userInput)) {
        console.log(`The string '${userInput}' is a palindrome.`);
    } else {
        console.log(`The string '${userInput}' is not a palindrome.`);
    }
    
    rl.close();
});
