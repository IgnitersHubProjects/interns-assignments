const fs = require("fs");
const path = require("path");

function isOp(ch) {
  return ch === "+" || ch === "-" || ch === "*" || ch === "/" || ch === "^";
}

function tokenize(expr) {
  const s = expr.replace(/\s+/g, "");
  const tokens = [];
  let i = 0;

  while (i < s.length) {
    const c = s[i];

    if (c >= "0" && c <= "9" || c === ".") {
      let j = i;
      while (j < s.length && ((s[j] >= "0" && s[j] <= "9") || s[j] === ".")) j++;
      tokens.push(s.slice(i, j));
      i = j;
      continue;
    }

    if (c === "(" || c === ")" || c === "[" || c === "]" || c === "{" || c === "}") {
      tokens.push(c);
      i++;
      continue;
    }

    if (isOp(c)) {
      tokens.push(c);
      i++;
      continue;
    }

    throw new Error("Invalid character: " + c);
  }

  return tokens;
}

function normalizeBrackets(tokens) {
  return tokens.map(t => {
    if (t === "[" || t === "{") return "(";
    if (t === "]" || t === "}") return ")";
    return t;
  });
}

function needsImplicitMultiply(prev, curr) {
  const prevIsNum = prev && !isNaN(prev);
  const currIsNum = curr && !isNaN(curr);

  const prevIsClose = prev === ")";
  const currIsOpen = curr === "(";

  return (prevIsNum && currIsOpen) || (prevIsClose && currIsOpen) || (prevIsClose && currIsNum) || (prevIsNum && currIsNum);
}

function insertImplicitMultiplication(tokens) {
  const out = [];
  for (let i = 0; i < tokens.length; i++) {
    const curr = tokens[i];
    const prev = out.length ? out[out.length - 1] : null;

    if (prev !== null && needsImplicitMultiply(prev, curr)) {
      if (!(isOp(prev) || prev === "(") && !(isOp(curr) || curr === ")")) {
        out.push("*");
      } else if ((prev === ")" || !isNaN(prev)) && curr === "(") {
        out.push("*");
      } else if (prev === ")" && !isNaN(curr)) {
        out.push("*");
      }
    }

    out.push(curr);
  }
  return out;
}

function handleUnary(tokens) {
  const out = [];
  for (let i = 0; i < tokens.length; i++) {
    const t = tokens[i];

    if ((t === "+" || t === "-")) {
      const prev = out.length ? out[out.length - 1] : null;
      const isUnary = prev === null || prev === "(" || isOp(prev);
      if (isUnary) {
        const next = tokens[i + 1];
        if (next === "(") {
          out.push("0");
          out.push(t);
        } else {
          out.push(t === "-" ? "u-" : "u+");
        }
        continue;
      }
    }

    out.push(t);
  }
  return out;
}

function precedence(op) {
  if (op === "u-" || op === "u+") return 5;
  if (op === "^") return 4;
  if (op === "*" || op === "/") return 3;
  if (op === "+" || op === "-") return 2;
  return 0;
}

function isRightAssociative(op) {
  return op === "^" || op === "u-" || op === "u+";
}

function toRPN(tokens) {
  const output = [];
  const stack = [];

  for (const t of tokens) {
    if (!isNaN(t)) {
      output.push(t);
      continue;
    }

    if (t === "(") {
      stack.push(t);
      continue;
    }

    if (t === ")") {
      while (stack.length && stack[stack.length - 1] !== "(") {
        output.push(stack.pop());
      }
      if (!stack.length) throw new Error("Mismatched parentheses");
      stack.pop();
      continue;
    }

    if (isOp(t) || t === "u-" || t === "u+") {
      while (stack.length) {
        const top = stack[stack.length - 1];
        if (top === "(") break;

        const p1 = precedence(t);
        const p2 = precedence(top);

        if ((isRightAssociative(t) && p1 < p2) || (!isRightAssociative(t) && p1 <= p2)) {
          output.push(stack.pop());
        } else break;
      }
      stack.push(t);
      continue;
    }

    throw new Error("Invalid token: " + t);
  }

  while (stack.length) {
    const top = stack.pop();
    if (top === "(") throw new Error("Mismatched parentheses");
    output.push(top);
  }

  return output;
}

function evalRPN(rpn) {
  const st = [];

  for (const t of rpn) {
    if (!isNaN(t)) {
      st.push(parseFloat(t));
      continue;
    }

    if (t === "u-" || t === "u+") {
      if (st.length < 1) throw new Error("Invalid unary operator usage");
      const a = st.pop();
      st.push(t === "u-" ? -a : +a);
      continue;
    }

    if (isOp(t)) {
      if (st.length < 2) throw new Error("Invalid operator usage");
      const b = st.pop();
      const a = st.pop();

      let res;
      if (t === "+") res = a + b;
      else if (t === "-") res = a - b;
      else if (t === "*") res = a * b;
      else if (t === "/") res = a / b;
      else if (t === "^") res = Math.pow(a, b);

      st.push(res);
      continue;
    }

    throw new Error("Invalid RPN token: " + t);
  }

  if (st.length !== 1) throw new Error("Invalid expression");
  return st[0];
}

function formatNumber(x) {
  if (!isFinite(x)) return String(x);
  if (Number.isInteger(x)) return String(x);
  return String(parseFloat(x.toFixed(12)));
}

function evaluateExpression(expr) {
  let tokens = tokenize(expr);
  tokens = normalizeBrackets(tokens);
  tokens = insertImplicitMultiplication(tokens);
  tokens = handleUnary(tokens);
  const rpn = toRPN(tokens);
  const result = evalRPN(rpn);
  return result;
}

function processLine(line) {
  const original = line.trimEnd();
  if (!original.trim()) return line;

  const eqIndex = original.indexOf("=");
  if (eqIndex === -1) return line;

  const left = original.slice(0, eqIndex).trim();
  if (!left) return line;

  const value = evaluateExpression(left);
  const ans = formatNumber(value);

  const normalizedLeft = original.slice(0, eqIndex).trimEnd();
  return `${normalizedLeft} = ${ans}`;
}

function main() {
  const inputFile = process.argv[2];
  const outputFile = process.argv[3] || "output.txt";

  if (!inputFile) {
    console.log("Usage: node eval_expressions.js <input_file.txt> [output_file.txt]");
    process.exit(1);
  }

  const inPath = path.resolve(inputFile);
  const outPath = path.resolve(outputFile);

  if (!fs.existsSync(inPath)) {
    console.log("Input file not found:", inPath);
    process.exit(1);
  }

  const lines = fs.readFileSync(inPath, "utf-8").split(/\r?\n/);

  const outLines = lines.map((ln) => {
    try {
      return processLine(ln);
    } catch (e) {
      const clean = ln.trimEnd();
      if (!clean) return ln;
      return `${clean}  [ERROR]`;
    }
  });

  fs.writeFileSync(outPath, outLines.join("\n"), "utf-8");
  console.log("Done. Output saved to:", outPath);
}

main();
