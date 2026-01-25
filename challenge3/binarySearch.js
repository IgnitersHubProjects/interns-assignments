const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19];
function binarySearch(array, target) {
  let left = 0;
  let right = array.length - 1;

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);
    if (array[mid] === target) {
      return mid;
    } else if (array[mid] < target) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }
  return -1;
}

rl.question("Enter the target element: ", (input) => {
  const target = parseInt(input);
  const result = binarySearch(arr, target);
  if (result !== -1) {
    console.log(`Element ${target} is found at index ${result}.`);
  } else {
    console.log(`Element ${target} is not found in the array.`);
  }
  rl.close();
});
