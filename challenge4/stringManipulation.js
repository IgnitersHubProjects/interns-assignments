const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("Enter a sentence: ", (sentence) => {
  const words = sentence.trim().split(/\s+/);
  const wordCount = words.length;
  const reversedSentence = words.reverse().join(" ");
  const modifiedSentence = sentence.trim().replace(/\s+/g, "-");
  console.log(`Number of words: ${wordCount}`);
  console.log(`Reversed sentence: ${reversedSentence}`);
  console.log(`Modified sentence: ${modifiedSentence}`);

  rl.close();
});
