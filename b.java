import java.util.Scanner;

public class b {
    
    public static int binarySearch(int[] array, int target) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        System.out.println("Enter the size of the sorted array:");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter the elements of the sorted array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        
        System.out.println("Enter the target element to search:");
        int target = scanner.nextInt();

        
        int result = binarySearch(array, target);

        
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }

        scanner.close();
    }
}
