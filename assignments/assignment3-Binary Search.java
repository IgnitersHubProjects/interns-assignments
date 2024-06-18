import java.util.Scanner;

public class BinarySearch {
    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.err.print("Enter the target element : ");
        int target = sc.nextInt();
        int result = binarySearch(array, target);
        sc.close();
        if (result != -1) {
            System.out.println("Element " + target + " found at index " + result);
        } else {
            System.out.println("Element "+ target + " is not present in the array");
        }
    }
}
