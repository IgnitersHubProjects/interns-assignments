// Challenge 4 - Binary Search
const readline = require('readline');

const arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19];

function binarySearch(a, target) {
  let lo = 0, hi = a.length - 1;
  while (lo <= hi) {
    const mid = Math.floor((lo + hi) / 2);
    if (a[mid] === target) return mid;
    if (a[mid] < target) lo = mid + 1;
    else hi = mid - 1;
  }
  return -1;
}

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
rl.question('Enter the target element: ', answer => {
  const target = Number(answer);
  console.log('The array: [' + arr.join(', ') + ']');
  if (answer.trim() === '' || Number.isNaN(target)) {
    console.log('Please enter a valid number.');
  } else {
    const idx = binarySearch(arr, target);
    console.log(idx !== -1 ? `Element ${target} is found at index ${idx}.` : `Element ${target} is not in the array.`);
  }
  rl.close();
});
