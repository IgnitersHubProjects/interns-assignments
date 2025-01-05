import PromptSync from 'prompt-sync'

const prompt = PromptSync()

const main = () => {
  const input = prompt('Enter a string: ')
  const trimmedInput = input.trim()
  const reversedString = trimmedInput.split('').reverse().join('')

  return reversedString === trimmedInput
    ? console.log('It is a palindrome')
    : console.log('It is not a palindrome')
}

main()
