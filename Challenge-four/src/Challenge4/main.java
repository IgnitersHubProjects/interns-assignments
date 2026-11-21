package Challenge4;
import java.util.Scanner;
public class main {
    public static void main(String[]args){
        System.out.println("Enter any sentence -- ");
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        System.out.println(countNumber(sentence));
        orderChange(sentence);
        replaceWithHyphen(sentence);




    }
    public static int countNumber(String s){
        int count =0;
        for(int i= 0;i<s.length();i++){
            count++;
        }
        return count;
    }
    public static void orderChange(String s){
        String word ="";
        for(int i =s.length()-1;i>=0;i--){

            char ch = s.charAt(i);
            if(ch != ' '){
            word = ch + word;}
            else {
                System.out.print(word+" ");
                System.out.println(" ");
                word ="";
            }



            }
        System.out.print(word);

        }
    public static void replaceWithHyphen(String s){

                String str =s.replace(' ','-');
                System.out.println(str);



    }
}


