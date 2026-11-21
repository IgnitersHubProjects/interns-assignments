// Challenge 1 - Approximate Search
// Usage: node challenge1_approximate_search.js [wordsFile] [k]
const fs = require('fs');
const path = require('path');
const readline = require('readline');

const file = process.argv[2] || path.join(__dirname, 'words.txt');
const K = parseInt(process.argv[3], 10) || 5;

// Load file into memory (deduplicated, lowercased)
const words = [...new Set(
  fs.readFileSync(file, 'utf8').split(/\r?\n/).map(w => w.trim().toLowerCase()).filter(Boolean)
)];

// Is `q` an in-order subsequence of `w`? (handles missing letters: "actv" -> "active")
function isSubsequence(q, w) {
  let i = 0;
  for (let j = 0; j < w.length && i < q.length; j++) if (w[j] === q[i]) i++;
  return i === q.length;
}

// Levenshtein edit distance (handles typos / dropped letters: "abslutly" -> "absolutely")
function editDistance(a, b) {
  let prev = Array.from({ length: b.length + 1 }, (_, i) => i);
  for (let i = 1; i <= a.length; i++) {
    const cur = [i];
    for (let j = 1; j <= b.length; j++) {
      cur[j] = Math.min(prev[j] + 1, cur[j - 1] + 1, prev[j - 1] + (a[i - 1] === b[j - 1] ? 0 : 1));
    }
    prev = cur;
  }
  return prev[b.length];
}

function suggest(query, k = K) {
  const q = query.trim().toLowerCase();
  if (!q) return [];
  const scored = [];
  for (const w of words) {
    const dist = editDistance(q, w);
    const maxDist = Math.max(2, Math.floor(q.length / 2));
    const sub = isSubsequence(q, w);
    if (!sub && dist > maxDist) continue;
    // Lower is better: prefix > subsequence > pure edit distance
    let score = dist;
    if (w.startsWith(q)) score = -2 + (w.length - q.length) / 100;
    else if (sub) score = -1 + (w.length - q.length) / 100;
    scored.push({ w, score });
  }
  return scored.sort((a, b) => a.score - b.score || a.w.localeCompare(b.w)).slice(0, k).map(s => s.w);
}

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
console.log(`Loaded ${words.length} words. Type a word (Ctrl+C to exit).`);
rl.setPrompt('Input >> ');
rl.prompt();
rl.on('line', line => {
  const res = suggest(line);
  console.log('Output >> ' + (res.length ? res.join(', ') : 'No suggestions found'));
  rl.prompt();
});
