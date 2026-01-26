const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.question("Enter a string : ", (input) => {
  const cleaned = input.replace(/[^a-zA-Z0-9]/g, "").toLowerCase();
  const reversed = cleaned.split("").reverse().join("");

  if (cleaned === reversed) {
    console.log(`The string '${input}' is a palindrome.`);
  } else {
    console.log(`The string '${input}' is not a palindrome.`);
  }

  rl.close();
});
