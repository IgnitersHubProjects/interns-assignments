// Function to perform binary search
function binarySearch(arr, target) {
    let left = 0;
    let right = arr.length - 1;

    while (left <= right) {
        const mid = Math.floor((left + right) / 2);

        if (arr[mid] === target) {
            return mid;
        }

        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}

const prompt = require('prompt-sync')();
const inputArray = prompt("Enter a sorted array : ").trim().split(' ')
    .map(Number);
const targetElement = parseInt(prompt("Enter the target element to search for: "), 10);

const result = binarySearch(inputArray, targetElement);
if (result === -1) {
    console.log("Element not present inside the array.");

} else {
    console.log(`Element is present at index: ${result}`);
}
