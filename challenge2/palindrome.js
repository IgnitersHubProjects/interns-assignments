const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("Enter a string: ", (input) => {
  const original = input.toLowerCase();
  const reversed = original.split("").reverse().join("");
  if (original === reversed) {
    console.log(`The string '${input}' is a palindrome.`);
  } else {
    console.log(`The string '${input}' is not a palindrome.`);
  }

  rl.close();
});
