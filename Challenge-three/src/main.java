import java.util.Scanner;
public class main {
    public static void main(String[]args){
        int binarySearch[] = new int []{1,3,5,7,9,11,13,15,17,19

        };
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any number : ");
        int userInput = sc.nextInt();

        int low = 0;
        int high = binarySearch.length;
        int mid=0;
        while (low<=high) {
            mid = low + (high - low) / 2;


            if (binarySearch[mid] == userInput) {
                System.out.println("Found the targeted item at index " + mid);
                return;
            } else if (userInput> binarySearch[mid]) {
                low = mid + 1;

            }
            else{
                high = mid-1;
            }

        }
        System.out.println("Element not found");


    }

}
