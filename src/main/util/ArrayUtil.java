package main.util;

public class ArrayUtil {

	/** From Career Cup
	 * You have an array of length n, containing values of length 0 to n-1. 
	 * Tell me if there exists a duplicate (return true or false). 
	 * Do it in constant space and O(n) time.
	 * @return
	 */
	public static boolean hasDouble(int [] array) {
		//Algorithm
		//go through array iteratively
		//each number you see corresponds to an index.  
		// - swap the current number array[x] with the index array[array[x]]
		//     you can do this since the array has values of length 0 - n-1 (so no error)
		// - before you swap, check what's in the index.  if the number in the index 
		//     equals the number you are trying to swap it with, there is a duplicate.
		//     if the number you are trying to swap array[x] 
		//	 Also - only swap if the number you are swapping with is in the wrong place
		for (int x = 0; x < array.length; x++) {
			printArray(array);
			int swapTo = array[x];
			if (swapTo != x) {	//swap array[x] with array[array[x]]
				if (array[swapTo] == array[x]) //collision
					return true;
				else {
					int temp = array[swapTo];
					array[swapTo] = array[x];
					array[x] = temp;
				}
			}
		}
		return false;
		
	}
	
	public static void printArray(int [] array) {
		for (int i : array) {
			System.out.print("[" + i + "] ");
		}
		System.out.println();
	}
	
	public static int[] addArrays(int [] a, int [] b) {
		int [] c = new int[Math.max(a.length, b.length) + 1];
		int i = Math.max(a.length, b.length) - 1;
		int j = Math.min(a.length, b.length) - 1;
		int carry = 0;
		
		//less code solution
		/* int actr = a.length - 1;
		 * int bctr = b.length - 1;
		 * while (i >= 0) {
		 * 	  	c[i] = carry;
		 * 		if (actr >= 0)
		 * 			c[i] += actr;
		 * 		if (bctr >= 0)
		 * 			c[i] += bctr;
		 * 	  	carry = c[i] / 10		
		 * 		c[i] = c[i] % 10;
		 * 	actr--;
		 *  bctr--;
		 *  i--;
		 * 		
		 * }
		 */
		
		//add all digits
		while (i >= 0 && j >= 0) {
			c[i+1] = (a[i] + b[j] + carry) % 10;
			carry = (a[i] + b[j] + carry) / 10;
			i--;
			j--;
		}
		//finish off digits
		
		while (i >= 0 || j >= 0) {
			if (a.length > b.length) {
				c[i+1] = (a[i] + carry) % 10;
				carry = (a[i] + carry) / 10;
			}
			else {
				c[i+1] = (b[i] + carry) % 10;
				carry = (b[i] + carry) / 10;
			}
		}
		
		c[0] = carry;
		
		return c;
	}
	
	public static String arrayValsToString(int [] a) {
		StringBuilder sb = new StringBuilder();
		for (int i : a) {
			sb.append(i);
		}
		return sb.toString();
	}
	
}
