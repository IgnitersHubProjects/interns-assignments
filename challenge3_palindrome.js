// Challenge 3 - Palindrome Checker
const readline = require('readline');

function isPalindrome(str) {
  const clean = str.toLowerCase().replace(/[^a-z0-9]/g, '');
  return clean.length > 0 && clean === [...clean].reverse().join('');
}

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
rl.question('Enter a string : ', answer => {
  console.log(`The string '${answer}' is ${isPalindrome(answer) ? 'a palindrome' : 'not a palindrome'}.`);
  rl.close();
});
