package ignitershub;
import java.util.Scanner;
public class Challenge4 {
	
	public void index(int ar[], int n)
	{
		
		for(int i=0;i<ar.length;i++)
		{
			if(ar[i]==n)
			{
				System.out.println("Element "+n+ " is found at index "+i+".");
			}
				
		}
		System.out.println("Element "+n+" is not in an Array.");
		
	}
	
	
	public static void main(String[] args) {
		
		Challenge4 ch = new Challenge4();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the target element: ");
		int n= s.nextInt();
		int ar[]= new int [] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
		
		ch.index(ar, n);
		
	}

}
