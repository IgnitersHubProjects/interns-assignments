const fs = require("fs");
const readline = require("readline");

const words = fs.readFileSync("words.txt", "utf-8")
  .split("\n")
  .map(w => w.trim())
  .filter(Boolean);

function levenshtein(a, b) {
  const dp = Array.from({ length: a.length + 1 }, () =>
    Array(b.length + 1).fill(0)
  );

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

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.question("Enter word: ", input => {
  const result = words
    .map(w => ({ w, d: levenshtein(input, w) }))
    .sort((a, b) => a.d - b.d)
    .slice(0, 3)
    .map(x => x.w);

  console.log("Suggestions:", result.join(", "));
  rl.close();
});
