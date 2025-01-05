import { readFile, writeFile } from 'fs'

function evaluateExpressions(inputFile, outputFile) {
  const preprocessExpression = (expression) => {
    return expression
      .replace(/\)\(/g, ')*(')
      .replace(/(\d)(\()/g, '$1*(')
      .replace(/(\))(\d)/g, ')*$2')
  }

  const evaluateExpression = (expression) => {
    try {
      expression = preprocessExpression(expression)
      expression = expression.replace(/\^/g, '**')
      const result = Function(`return (${expression});`)()
      return result
    } catch (error) {
      return `Error: ${error.message}`
    }
  }

  readFile(inputFile, 'utf8', (err, data) => {
    if (err) {
      console.error(`Error reading input file: ${err.message}`)
      return
    }

    const lines = data.split('\n')
    const results = lines.map((line) => {
      const match = line.match(/^(.*?)(\s*=)\s*$/)
      console.log(match)
      if (match) {
        const expression = match[1].trim()
        if (expression) {
          const result = evaluateExpression(expression)
          return `${expression} = ${result}`
        } else {
          return `Invalid expression: ${line}`
        }
      } else {
        return `Invalid expression: ${line}`
      }
    })

    writeFile(outputFile, results.join('\n'), 'utf8', (err) => {
      if (err) {
        console.error(`Error writing output file: ${err.message}`)
      } else {
        console.log(`Results written to ${outputFile}`)
      }
    })
  })
}

const inputFile = 'expressions.txt'
const outputFile = 'output.txt'

evaluateExpressions(inputFile, outputFile)
