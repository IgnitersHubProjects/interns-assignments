import fs from 'fs'
import PromptSync from 'prompt-sync'

const prompt = PromptSync()

function levenshteinDistance(a, b) {
  const dp = Array.from({ length: a.length + 1 }, () =>
    Array(b.length + 1).fill(0)
  )

  for (let i = 0; i <= a.length; i++) dp[i][0] = i
  for (let j = 0; j <= b.length; j++) dp[0][j] = j

  for (let i = 1; i <= a.length; i++) {
    for (let j = 1; j <= b.length; j++) {
      if (a[i - 1] === b[j - 1]) {
        dp[i][j] = dp[i - 1][j - 1]
      } else {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
      }
    }
  }

  return dp[a.length][b.length]
}

function getClosestWords(inputWord, words, topK = 3) {
  const distances = words.map((word) => ({
    word,
    distance: levenshteinDistance(inputWord, word),
  }))

  distances.sort((a, b) => a.distance - b.distance)
  return distances.slice(0, topK).map((d) => d.word)
}

const fileName = prompt('Enter the file name: ')
try {
  const file = fs.readFileSync(fileName, 'utf8')
  const words = file.split(/\s+/)

  console.log(
    "File loaded successfully! Type a word to search for suggestions. Type 'exit' to quit.\n"
  )

  while (true) {
    const input = prompt('Enter a word: ').trim()
    if (input.toLowerCase() === 'exit') {
      console.log('Exiting the program. Goodbye!')
      break
    }

    const suggestions = getClosestWords(input, words)
    console.log(
      `Suggestions: ${suggestions.join(', ') || 'No suggestions found.'}`
    )
  }
} catch (error) {
  console.error(
    'Error reading the file. Please check the file path and try again.'
  )
}
