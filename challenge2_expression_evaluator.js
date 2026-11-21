// Challenge 2 - Evaluate Arithmetic Expressions

const fs = require('fs');
const path = require('path');

const inFile = process.argv[2] || path.join(__dirname, 'input.txt');
const outFile = process.argv[3] || path.join(__dirname, 'output.txt');

function tokenize(src) {
  const s = src.replace(/[\u2013\u2014\u2212]/g, '-').replace(/\[|\{/g, '(').replace(/\]|\}/g, ')');
  const tokens = [];
  const re = /\s*(\d+\.?\d*|\.\d+|[-+*\/^()])/y;
  let pos = 0, m;
  while (pos < s.length) {
    if (/^\s*$/.test(s.slice(pos))) break;
    re.lastIndex = pos;
    m = re.exec(s);
    if (!m) throw new Error(`Unexpected character near "${s.slice(pos).trim()[0]}"`);
    tokens.push(m[1]);
    pos = re.lastIndex;
  }
  return tokens;
}

function evaluate(src) {
  const t = tokenize(src);
  let i = 0;
  const isNum = x => x !== undefined && /^[\d.]/.test(x);

  // expr := term (('+'|'-') term)*
  function expr() {
    let v = term();
    while (t[i] === '+' || t[i] === '-') v = t[i++] === '+' ? v + term() : v - term();
    return v;
  }
  // term := unary (('*'|'/'|implicit) unary)*
  function term() {
    let v = unary();
    for (;;) {
      if (t[i] === '*') { i++; v *= unary(); }
      else if (t[i] === '/') {
        i++; const d = unary();
        if (d === 0) throw new Error('Division by zero');
        v /= d;
      } else if (t[i] === '(' || isNum(t[i])) v *= unary(); // implicit multiplication
      else return v;
    }
  }
  // unary := ('-'|'+') unary | power
  function unary() {
    if (t[i] === '-') { i++; return -unary(); }
    if (t[i] === '+') { i++; return unary(); }
    return power();
  }
  // power := primary ('^' unary)?   (right-associative)
  function power() {
    const base = primary();
    if (t[i] === '^') { i++; return Math.pow(base, unary()); }
    return base;
  }
  function primary() {
    const tok = t[i];
    if (tok === '(') {
      i++; const v = expr();
      if (t[i++] !== ')') throw new Error('Missing closing bracket');
      return v;
    }
    if (isNum(tok)) { i++; return parseFloat(tok); }
    throw new Error('Unexpected end or token');
  }

  const result = expr();
  if (i < t.length) throw new Error('Unexpected token "' + t[i] + '"');
  return result;
}

const lines = fs.readFileSync(inFile, 'utf8').split(/\r?\n/);
const out = lines.map(line => {
  if (!line.trim()) return line;
  const exprText = line.replace(/=\s*$/, '').trim();
  try {
    const r = Math.round(evaluate(exprText) * 1e10) / 1e10;
    return `${exprText} = ${r}`;
  } catch (e) {
    return `${exprText} = Error: ${e.message}`;
  }
});
fs.writeFileSync(outFile, out.join('\n') + '\n');
console.log(`Solved ${lines.filter(l => l.trim()).length} expressions -> ${outFile}`);
