package pyramidCode;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   //Rewrite so that it uses get on a url input and based on the boolean reponse returns the correct http response
	public static void main(String[] args) {
	   System.out.print("Enter string: ");
	   Scanner input = new Scanner(System.in);
	   String in = input.nextLine();
	   System.out.print(test(in));
   }
	
	public static boolean test(String args) {
      String x = args;
      char[] letter = x.toCharArray();
      Arrays.sort(letter);
      char temp = letter[0];
      int[] count = new int [letter.length];
      int n = 0;
      count[n]=1;
      for(int i = 1; i < letter.length; i++) {
    	  if(letter[i] == temp) {
    		  //add to arraylist
    		  count[n] = count[n] + 1;
    	  } else {
    		  temp = letter[i];
    		  n++;
    		  count[n] = 0;
    		  count[n] = count[n] + 1;
    	  }
      }
      Arrays.sort(count);
      int a = 1;
      for(int i = 0; i<count.length; i++) {
    	  if(count[i] == 0) {
    		  continue;
    	  } else if (count[i] != a) {
    		  return false;
    	  } else {
    		  ++a;
    		  continue;
    	  }
      }
      return true;
}}
