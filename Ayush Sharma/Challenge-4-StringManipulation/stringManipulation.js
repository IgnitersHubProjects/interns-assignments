const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question('Enter a sentence: ', (sentence) => {
  const clean = sentence.trim();
  const words = clean.split(/\s+/);

  const wordCount = words.length;
  const reversedSentence = words.slice().reverse().join(' ');
  const modifiedSentence = clean.replace(/\s+/g, '-');

  console.log(`Number of words: ${wordCount}`);
  console.log(`Reversed sentence: ${reversedSentence}`);
  console.log(`Modified sentence: ${modifiedSentence}`);

  rl.close();
});
