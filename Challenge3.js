import readline from "readline";

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.question("Enter a string : ", (userInput) => {
  const text = userInput.toLowerCase();
  let left = 0;
  let right = text.length - 1;
  let isPalindrome = true;

  while (left < right) {
    if (text[left] !== text[right]) {
      isPalindrome = false;
      break;
    }
    left++;
    right--;
  }

  if (isPalindrome) {
    console.log(`The string '${userInput}' is a palindrome`);
  } else {
    console.log(`The string '${userInput}' is not a palindrome`);
  }
  rl.close();
});
