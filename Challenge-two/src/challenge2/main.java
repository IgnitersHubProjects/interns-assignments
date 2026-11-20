package challenge2;
import  java.util.Scanner;

public class main {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String - ");
         String userInput = sc.nextLine();
         String reverse ="";
         for(int i =userInput.length()-1;i>=0;i--){
                reverse = reverse + userInput.charAt(i);


         }
         if(userInput.equalsIgnoreCase(reverse)){
             System.out.println("It is a palindrome.");
         }
         else {
             System.out.println("it's not a palindrome.");
         }
         sc.close();


    }


}
