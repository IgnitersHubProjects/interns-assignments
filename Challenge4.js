import readline from "readline";

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});
rl.question("Enter a sentence: ", (sentence) => {
  const words = sentence.trim().split(/\s+/);
  const wordCount = words.length;
  const reversedSentence = words.slice().reverse().join(" ");
  const HyphonSentence = sentence.trim().split(/\s+/).join("-");

  console.log(`Number of words: ${wordCount}`);
  console.log(`Reversed sentence: ${reversedSentence}`);
  console.log(`Hyphon sentence: ${HyphonSentence}`);

  rl.close();
});
