def binary_search(arr, target):
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (low + high) // 2

        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return -1
arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
target = int(input("Enter the target element: "))
print(f"The array: {arr}")
result = binary_search(arr, target)
if result != -1:
    print(f"Element {target} is found at index {result}.")
else:
    print(f"Element {target} is not in the array.")