const readline = require("readline");

function isPalindrome(str) {
  const cleaned = str.toLowerCase().replace(/[^a-z0-9]/g, "");
  let i = 0;
  let j = cleaned.length - 1;
  while (i < j) {
    if (cleaned[i] !== cleaned[j]) return false;
    i++;
    j--;
  }
  return cleaned.length > 0;
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("Enter a string : ", (input) => {
  const ok = isPalindrome(input);
  if (ok) {
    console.log(`The string '${input}' is a palindrome.`);
  } else {
    console.log(`The string '${input}' is not a palindrome.`);
  }
  rl.close();
});
