const http = require('http');
const fs = require('fs');
const url = require('url');

let words = [];

// Load words from a file
fs.readFile('words.txt', 'utf8', (err, data) => {
  if (err) {
    console.error("Failed to load words.txt:", err);
    return;
  }
  words = data.split(/\r?\n/).filter(Boolean);
});

// Function to calculate Levenshtein Distance
function levenshteinDistance(a, b) {
  const dp = Array.from({ length: a.length + 1 }, () => Array(b.length + 1).fill(0));

  for (let i = 0; i <= a.length; i++) dp[i][0] = i;
  for (let j = 0; j <= b.length; j++) dp[0][j] = j;

  for (let i = 1; i <= a.length; i++) {
    for (let j = 1; j <= b.length; j++) {
      dp[i][j] = Math.min(
        dp[i - 1][j] + 1,
        dp[i][j - 1] + 1,
        dp[i - 1][j - 1] + (a[i - 1] === b[j - 1] ? 0 : 1)
      );
    }
  }
  return dp[a.length][b.length];
}

// Approximate search
function approximateSearch(input, k = 3) {
  return words
    .map(word => ({ word, distance: levenshteinDistance(input, word) }))
    .sort((a, b) => a.distance - b.distance)
    .slice(0, k)
    .map(entry => entry.word);
}

const server = http.createServer((req, res) => {
  const queryObject = url.parse(req.url, true).query;

  if (req.url.startsWith('/search')) {
    const input = queryObject.q || '';
    const suggestions = approximateSearch(input);
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(suggestions));
  } else {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    fs.createReadStream('index.html').pipe(res);
  }
});

server.listen(3000, () => {
  console.log('Server running at http://localhost:3000/');
});
