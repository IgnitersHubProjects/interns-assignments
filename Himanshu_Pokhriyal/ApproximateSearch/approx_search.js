const fs = require("fs");
const readline = require("readline");
const path = require("path");

const DEFAULT_TOP_K = 5;

function levenshteinDistance(a, b) {
  a = a.toLowerCase();
  b = b.toLowerCase();

  const m = a.length;
  const n = b.length;

  const dp = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

  for (let i = 0; i <= m; i++) dp[i][0] = i;
  for (let j = 0; j <= n; j++) dp[0][j] = j;

  for (let i = 1; i <= m; i++) {
    for (let j = 1; j <= n; j++) {
      const cost = a[i - 1] === b[j - 1] ? 0 : 1;
      dp[i][j] = Math.min(
        dp[i - 1][j] + 1,
        dp[i][j - 1] + 1,
        dp[i - 1][j - 1] + cost
      );
    }
  }

  return dp[m][n];
}

function isSubsequence(small, big) {
  let i = 0,
    j = 0;
  while (i < small.length && j < big.length) {
    if (small[i] === big[j]) i++;
    j++;
  }
  return i === small.length;
}

function similarityScore(query, word) {
  query = query.toLowerCase();
  word = word.toLowerCase();

  const dist = levenshteinDistance(query, word);

  let prefixBonus = 0;
  let i = 0;
  while (i < query.length && i < word.length && query[i] === word[i]) {
    prefixBonus++;
    i++;
  }

  const subseqBonus = isSubsequence(query, word) ? 2 : 0;

  return dist - prefixBonus * 0.2 - subseqBonus;
}

function loadWordsFromFile(filePath) {
  const fullPath = path.resolve(filePath);

  if (!fs.existsSync(fullPath)) {
    console.log(`File not found: ${fullPath}`);
    process.exit(1);
  }

  const content = fs.readFileSync(fullPath, "utf-8");

  const words = content
    .split(/\s+/)
    .map((w) => w.trim())
    .filter(Boolean);

  return [...new Set(words)];
}

function approximateSearch(query, words, k) {
  query = query.trim();
  if (!query) return [];

  const scored = words.map((word) => ({
    word,
    score: similarityScore(query, word),
  }));

  scored.sort((a, b) => a.score - b.score);

  return scored.slice(0, k).map((x) => x.word);
}

function startInteractiveSearch(words, k) {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    prompt: "Input >> ",
  });

  rl.prompt();

  rl.on("line", (line) => {
    const query = line.trim();

    if (query.toLowerCase() === "exit") {
      rl.close();
      return;
    }

    const results = approximateSearch(query, words, k);

    if (results.length === 0) {
      console.log("Output >>");
    } else {
      console.log("Output >> " + results.join(", "));
    }

    rl.prompt();
  });

  rl.on("close", () => process.exit(0));
}

(function main() {
  const filePath = process.argv[2];
  const topK = parseInt(process.argv[3] || DEFAULT_TOP_K, 10);

  if (!filePath) {
    console.log("Usage: node approx_search.js <dictionary_file.txt> [topK]");
    process.exit(1);
  }

  const words = loadWordsFromFile(filePath);
  startInteractiveSearch(words, topK);
})();
