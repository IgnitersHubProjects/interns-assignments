// Function to check if a string is a palindrome
function isPalindrome(input) {
    const Input = input.replace(/[^a-zA-Z0-9]/g, '').toLowerCase();
    return Input === Input.split('').reverse().join('');
}

const userInput = prompt("Enter a string to check if it's a palindrome:");

if (userInput !== null && userInput.trim() !== "") {
    const result = isPalindrome(userInput)
        ? `"${userInput}" is a palindrome!`
        : `"${userInput}" is not a palindrome.`;
    alert(result);
} else {
    alert("Invalid input. Please enter a valid string.");
}