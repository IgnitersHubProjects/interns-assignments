
public class main {
    public static void main(String[]args){
        int binarySearch[] = new int []{1,3,5,7,9,11,13,15,17,19

        };
        int item = 11;
        int low = 0;
        int high = binarySearch.length;
        int mid=0;
        while (low<=high) {
            mid = low + (high - low) / 2;


            if (binarySearch[mid] == item) {
                System.out.println("Found the targeted item at index " + mid);
                return;
            } else if (item > binarySearch[mid]) {
                low = mid + 1;

            }
            else{
                high = mid-1;
            }

        }
        System.out.println("Element not found");


    }

}
