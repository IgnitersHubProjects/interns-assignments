
function isPalindrome(str) {
 
    const cleanStr = str.replace(/[^a-zA-Z0-9]/g, '').toLowerCase();
    
    const reversedStr = cleanStr.split('').reverse().join('');
    return cleanStr === reversedStr;
  }
  
  
  const readline = require('readline');
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });
  
  rl.question("Enter a string: ", function(inputString) {
    if (isPalindrome(inputString)) {
      console.log(`The string '${inputString}' is a palindrome.`);
    } else {
      console.log(`The string '${inputString}' is not a palindrome.`);
    }
    rl.close();
  });
  