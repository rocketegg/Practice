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
		// - take care of the condition of first and last elements 
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
	
}
