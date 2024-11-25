const fs = require("fs");

function evaluateExpression(expression) {
  try {
    return eval(expression.replace(/\^/g, '**')); 
  } catch {
    return "Error"; 
  }
}

function processFile(inputFile, outputFile) {
  try {
    const data = fs.readFileSync(inputFile, "utf8");
    const results = data.split("\n").map(line => {
      if (!line.trim()) return ""; // Skip empty lines

      const [expression] = line.split("=");
      const result = evaluateExpression(expression.trim());
      return result === "Error" ? `${line} Error` : `${line.trim()} ${result}`;
    });

    fs.writeFileSync(outputFile, results.join("\n"), "utf8");
    console.log(`Output written to ${outputFile}`);
  } catch (error) {
    console.error("Error processing file:", error.message);
  }
}

processFile("input.txt", "output.txt");
