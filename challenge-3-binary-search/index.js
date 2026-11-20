const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Sorted array
const arr = [10, 20, 30, 40, 50, 60, 70, 80, 90];

function binarySearch(arr, target) {
    let left = 0;
    let right = arr.length - 1;

    while (left <= right) {
        let mid = Math.floor((left + right) / 2);

        if (arr[mid] === target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}

console.log("Array:", arr);

rl.question("Enter the number to search: ", function (input) {
    const target = Number(input);
    const result = binarySearch(arr, target);

    if (result !== -1) {
        console.log("Element found at index:", result);
    } else {
        console.log("Element not found.");
    }

    rl.close();
});