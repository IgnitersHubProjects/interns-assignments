function binarySearch(arr, target) {
    let left = 0;
    let right = arr.length - 1;
    
    while (left <= right) {
        let mid = Math.floor((left + right) / 2);
        
        if (arr[mid] === target) {
            return mid; // Target element found
        } else if (arr[mid] < target) {
            left = mid + 1; // Search in the right half
        } else {
            right = mid - 1; // Search in the left half
        }
    }
    return -1; // Target element not found
}

let sortedArray = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19];

let userInput = prompt("Enter the target element to search for:");

let targetElement = parseInt(userInput, 10);

let result = binarySearch(sortedArray, targetElement);

let ans = ""

if (result !== -1) {
    ans = `Element ${targetElement} is found at index ${result}.`;
} else {
    ans = "Element not found in the array."
}

alert(ans);
