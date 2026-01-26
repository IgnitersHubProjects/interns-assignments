const fs = require("fs");

const lines = fs.readFileSync("input.txt", "utf-8").split("\n");
let output = [];

for (let line of lines) {
  if (!line.trim()) continue;

  let expr = line.replace("=", "").trim();
  expr = expr.replace(/\^/g, "**");

  const result = Function(`return ${expr}`)();
  output.push(`${line} ${result}`);
}

fs.writeFileSync("output.txt", output.join("\n"));
console.log("Evaluation completed.");
