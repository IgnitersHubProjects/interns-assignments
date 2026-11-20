const readline = require('readline');

const arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19];

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

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question('Enter the target element: ', (input) => {
  const target = parseInt(input.trim(), 10);

  console.log(`The array: [${arr.join(', ')}]`);

  const index = binarySearch(arr, target);

  if (index !== -1) {
    console.log(`Element ${target} is found at index ${index}.`);
  } else {
    console.log(`Element ${target} is not in the array.`);
  }

  rl.close();
});
