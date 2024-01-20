package ignitershub;

import java.util.*;


public class Challenge1 {

    public static void main(String[] args) {

    	Scanner s = new Scanner(System.in);
    
        String s1 = s.nextLine();
        String s2 = s.nextLine();

        Set<Character> set1 = new HashSet<Character>();
        Set<Character> set2 = new HashSet<Character>();

        for(char c : s1.toCharArray()) {
            set1.add(c);
        }
        for(char c : s2.toCharArray()) {
            set2.add(c);
        }

        
        set1.retainAll(set2);

        for(char c : set1) {
            System.out.print(" " + c);
        }

       
    }
}