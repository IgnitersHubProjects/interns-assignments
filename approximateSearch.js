const fs = require("fs");
const readline = require("readline");
const levenshtein = require("fast-levenshtein");


const loadStringsFromFile = (filePath) => {
  try {
    const data = fs.readFileSync(filePath, "utf8");
    return data
      .split("\n")
      .map((line) => line.trim())
      .filter((line) => /^[a-zA-Z0-9]+$/.test(line)); 
  } catch (err) {
    console.error("Error reading file:", err);
    process.exit(1);
  }
};


const findSimilarStrings = (input, strings, k = 3) => {
  input = input.toLowerCase();

  
  const results = strings.map((str) => {
    const lowerStr = str.toLowerCase();
    const distance = levenshtein.get(input, lowerStr);
    const normalizedDistance = distance / Math.max(input.length, lowerStr.length);
    const isPrefixMatch = lowerStr.startsWith(input); 
    return { word: str, distance: normalizedDistance, isPrefixMatch };
  });

 
  results.sort((a, b) => {
    if (a.isPrefixMatch && !b.isPrefixMatch) return -1;
    if (!a.isPrefixMatch && b.isPrefixMatch) return 1;
    return a.distance - b.distance;
  });

 
  return results.slice(0, k).map((entry) => entry.word);
};


const main = () => {
  const filePath = "strings.txt";
  const strings = loadStringsFromFile(filePath);

  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  console.log("Approximate Search Program");
  console.log("Enter a word (or type 'exit' to quit):");

  rl.on("line", (input) => {
    if (input.toLowerCase() === "exit") {
      console.log("Goodbye!");
      rl.close();
      process.exit(0);
    }

    const suggestions = findSimilarStrings(input, strings, 3);
    if (suggestions.length > 0) {
      console.log("Suggestions:", suggestions.join(", "));
    } else {
      console.log("No suggestions found.");
    }

    console.log("\nEnter another word (or type 'exit' to quit):");
  });
};

main();
