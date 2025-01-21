import java.util.Scanner;
import java.util.Arrays;

class BinarySearch {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input the size of the array
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        // Create an array of the specified size
        int[] array = new int[size];

        // Prompt the user to input the array elements
        System.out.println("Enter the array elements (sorted in ascending order):");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // Sort the array to ensure it is in ascending order (if not already sorted)
        Arrays.sort(array);

        // Prompt the user to enter the target element
        System.out.print("Enter the target element: ");
        int target = scanner.nextInt();

        // Perform binary search using the recursive method
        int result = binarySearch(array, 0, array.length - 1, target);

        // Display the result
        if (result == -1) {
            System.out.println("Element " + target + " is not in the array.");
        } else {
            System.out.println("Element " + target + " is found at index " + result + ".");
        }

        // Close the scanner
        scanner.close();
    }

    // Recursive binary search method
    public static int binarySearch(int[] array, int left, int right, int target) {
        // Base case: If the search space is invalid, the element is not found
        if (left > right) {
            return -1;
        }

        // Calculate the mid index
        int mid = left + (right - left) / 2;

        // Check if the target is at mid
        if (array[mid] == target) {
            return mid; // Target found
        }

        // If the target is smaller, search the left half
        if (array[mid] > target) {
            return binarySearch(array, left, mid - 1, target);
        }

        // If the target is larger, search the right half
        return binarySearch(array, mid + 1, right, target);
    }
}
