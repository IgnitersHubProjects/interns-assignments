const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question('Enter a string : ', (str) => {
  const original = str.trim();
  const reversed = original.split('').reverse().join('');

  if (original.toLowerCase() === reversed.toLowerCase()) {
    console.log(`The string ‘${original}’ is a palindrome.`);
  } else {
    console.log(`The string ‘${original}’ is not a palindrome.`);
  }

  rl.close();
});
