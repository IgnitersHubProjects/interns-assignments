const fs = require('fs');
const readline = require('readline');
const stringSimilarity = require('string-similarity');

function loadStringsFromFile(filePath) {
  try {
    const data = fs.readFileSync(filePath, 'utf8');
    return data.split('\n').map(line => line.trim()).filter(line => line.length > 0);
  } catch (error) {
    console.error('Error reading file:', error.message);
    return [];
  }
}

function findSimilarStrings(input, strings, k) {
  const matches = stringSimilarity.findBestMatch(input, strings);
  return matches.ratings
    .sort((a, b) => b.rating - a.rating) 
    .slice(0, k) 
    .map(match => ({ string: match.target, similarity: match.rating })); 
}

function main() {
  const strings = loadStringsFromFile('data.txt'); 
  if (strings.length === 0) {
    console.log('No strings found in the file.');
    return;
  }

  console.log('Loaded strings from the file.');

  const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

  function askUserInput() {
    rl.question('Enter a word (or type "exit" to quit): ', (input) => {
      if (input.trim().toLowerCase() === 'exit') {
        console.log('Goodbye!');
        rl.close();
        return;
      }

      const k = 5; 
      const suggestions = findSimilarStrings(input.trim(), strings, k);

      console.log('Top suggestions:');
      suggestions.forEach((s, index) => {
        console.log(`${index + 1}. ${s.string} (Similarity: ${s.similarity.toFixed(2)})`);
      });

      askUserInput(); 
    });
  }

  askUserInput(); 
}

main(); 
