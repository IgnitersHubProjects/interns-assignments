const fs = require("fs");

// Convert expression to valid JavaScript format
function normalizeExpression(expr) {
    return expr
        .replace(/–/g, "-")                // Replace unicode minus with normal minus
        .replace(/\^/g, "**")              // Replace ^ with JS exponent **
        .replace(/\)\s*\(/g, ")*(")        // Fix implicit multiplication )( → )*(
        .replace(/(\d)\s*\(/g, "$1*(")     // Fix implicit multiplication 2( → 2*(
        .replace(/\)\s*(\d)/g, ")*$1")     // Fix implicit multiplication )2 → )*2
        .trim();
}

// Safely evaluate arithmetic expression
function evaluateExpression(expr) {
    try {
        const normalized = normalizeExpression(expr);
        const result = Function(`"use strict"; return (${normalized})`)();
        return result;
    } catch (error) {
        return "Invalid Expression";
    }
}

// Read input file
const input = fs.readFileSync("input.txt", "utf8");

// Process each line
const output = input
    .split("\n")
    .map(line => {
        if (!line.trim()) return "";

        const expression = line.replace("=", "").trim();
        const answer = evaluateExpression(expression);
        return `${expression} = ${answer}`;
    })
    .join("\n");

// Write output file
fs.writeFileSync("output.txt", output);

console.log("Output file generated successfully.");