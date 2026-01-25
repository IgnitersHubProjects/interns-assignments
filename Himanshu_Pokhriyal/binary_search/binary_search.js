const readline = require("readline");

function binarySearch(arr, target) {
  let left = 0;
  let right = arr.length - 1;

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);

    if (arr[mid] === target) return mid;
    if (arr[mid] < target) left = mid + 1;
    else right = mid - 1;
  }

  return -1;
}

const arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19];

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("Enter the target element: ", (input) => {
  const target = Number(input.trim());

  if (Number.isNaN(target)) {
    console.log("Invalid input. Please enter a number.");
    rl.close();
    return;
  }

  console.log("The array:", JSON.stringify(arr));

  const idx = binarySearch(arr, target);

  if (idx !== -1) {
    console.log(`Element ${target} is found at index ${idx}.`);
  } else {
    console.log(`Element ${target} is not in the array.`);
  }

  rl.close();
});
