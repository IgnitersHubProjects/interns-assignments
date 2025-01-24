const fs = require("fs");


const evaluateExpression = (expression) => {
  try {
 
    const formattedExpression = expression.replace(/\^/g, "**");

    return eval(formattedExpression);
  } catch (error) {
    return "Error";
  }
};


const main = () => {
  const inputFile = "input.txt"; // Input file
  const outputFile = "output.txt"; // Output file

  try {
    const data = fs.readFileSync(inputFile, "utf8");
    const lines = data.split("\n").map((line) => line.trim()).filter(Boolean);

    const results = lines.map((line) => {
      const [expression] = line.split("="); // Extract expression before "="
      if (expression) {
        const result = evaluateExpression(expression.trim());
        return `${line.trim()} ${result}`;
      }
      return `${line.trim()} Error`;
    });

    
    fs.writeFileSync(outputFile, results.join("\n"), "utf8");
    console.log(`Results have been written to ${outputFile}`);
  } catch (error) {
    console.error("Error:", error.message);
  }
};


main();
