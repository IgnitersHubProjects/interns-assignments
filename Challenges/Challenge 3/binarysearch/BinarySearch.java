package binarysearch;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

	    public static void main(String[] args) {
	        
	        Scanner scanner = new Scanner(System.in);
	      
	        System.out.print("Enter the size of the sorted array: ");
	        int size = scanner.nextInt();
	        
	        int[] sortedArray = new int[size];

	        System.out.println("Enter the sorted elements of the array:");
	        for (int i = 0; i < size; i++) {
	            sortedArray[i] = scanner.nextInt();
	        }

	        Arrays.sort(sortedArray);
	       
	        System.out.print("Enter the target element: ");
	        int target = scanner.nextInt();

	        int result = binarySearch(sortedArray, target);
	        if (result != -1) {
	            System.out.println("Element " + target + " is found at index " + result);
	        } else {
	            System.out.println("Element " + target + " is not found in the array.");
	        }

	        scanner.close();
	    }

	    private static int binarySearch(int[] array, int target) {
	        int left = 0;
	        int right = array.length - 1;

	        while (left <= right) {
	            int mid = left + (right - left) / 2;

	            if (array[mid] == target) {
	                return mid;
	            }

	            if (array[mid] < target) {
	                left = mid + 1;
	            }

	            else {
	                right = mid - 1;
	            }
	        }

	        return -1;
	    }
	}
