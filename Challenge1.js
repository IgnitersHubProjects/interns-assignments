import fs from "fs";

const inputFile="input.txt";
const outputFile="output.txt";  

const fileContent = fs.readFileSync(inputFile, "utf-8");
const lines = fileContent.split("\n");

const results = [];

for (const line of lines) {
    if (!line.trim()) continue;
    let expression = line.replace("=", "").trim();
     expression = expression.replace(/\^/g, "**");

      try {

    const answer = eval(expression);

    results.push(`${line.trim()} ${answer}`);
  } catch (error) {

    results.push(`${line.trim()} ERROR`);
  }
}
fs.writeFileSync(outputFile, results.join("\n"));
console.log(`Results successfully written to ${outputFile}`);