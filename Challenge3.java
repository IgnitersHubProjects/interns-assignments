package ignitershub;
import java.util.Scanner;

public class Challenge3 {

	
	Scanner s = new Scanner(System.in);
	public void palli()
	{
		System.out.println("Enter String");
		String k = s.nextLine();
		
		String l = "";
		char c;
		
		for(int i=0; i<k.length(); i++)
		{
			c = k.charAt(i);
			l=c+l;
		}
		
		 System.out.println(l);
		if(k.equals(l))
		{
			System.out.println("The String '"+k+"' is a palindrome.");
		}
		else
		{
			System.out.println("Not palondrome.");
		}
	}
	
	
	public static void main(String[] args) {
		
		Challenge3  a= new Challenge3();
		a.palli();
		
	}

}
