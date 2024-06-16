function isPalindrome(str) {
  let revStr = "";
  for (let i = str.length - 1; i >= 0; i--) {
    revStr += str[i];
  }
  if (str === revStr) return true;
  return false;
}

let userInput = prompt("Enter a string to check if it is a palindrome:");

let ans = "";

if (isPalindrome(userInput)) {
  ans = `The string '${userInput}' is a palindrome`;
} else {
  ans = "The string is not a palindrome.";
}

alert(ans);
