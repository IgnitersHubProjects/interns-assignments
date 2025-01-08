import java.util.*;

class BinarySearch {
    Scanner input = new Scanner(System.in);
    int left, right, mid;
    int[] a = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    int size, data;

    void binary() {
        size = a.length;
        left = 0;
        right = size - 1;

        System.out.print("The array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");

        System.out.print("Enter the element to search: ");
        data = input.nextInt();

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (a[mid] == data) {
                System.out.println("Element " + data + " found at index: " + mid);
                return;
                
            } else if (a[mid] < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Element not found");
    }
}

class challenge_3 {
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        bs.binary();
    }
}
