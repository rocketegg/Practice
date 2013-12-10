package main.util;

import java.util.Arrays;

public class ArrayUtil {

	/** From Career Cup
	 * You have an array of length n, containing values of length 0 to n-1. 
	 * Tell me if there exists a duplicate (return true or false). 
	 * Do it in constant space and O(n) time.
	 * @return
	 */
	public static boolean hasDouble(Integer [] array) {
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
	
	public static <T> void printArray(T [] array) {
		for (T i : array) {
			System.out.print("[" + i.toString() + "] ");
		}
		System.out.println();
	}
	
	/**
	 * This function takes two arrays that represent big integers
	 * [9][4][3][2]....[3] (n digits)
	 * and adds them together returning another array in the process
	 * Approach: Create new array of size a.length and b.length
	 *   - Iterate through and carry the carry through each time
	 *   - store the digit which is the sum % 10
	 * @param a
	 * @param b
	 * @return
	 */
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
		return (c[0] == 0) ? Arrays.copyOfRange(c, 1, c.length) : c;
	}
	
	/**
	 * This function takes an array representing integers in a string and 
	 * concatenates them into a string
	 * @param a
	 * @return
	 */
	public static String arrayValsToString(int [] a) {
		StringBuilder sb = new StringBuilder();
		for (int i : a) {
			sb.append(i);
		}
		return sb.toString();
	}
	
	/**
	 * This function takes a list of integers and uses merge sort to sort them
	 * and returns a new array
	 * @param list
	 * @return
	 */
	public static Integer [] mergeSort(Integer [] list) {
		if (list.length == 1)
			return list;
		int middle = list.length/2;
		Integer [] left = new Integer [middle];
		for (int x = 0; x < middle; x++) 
			left[x] = list[x];
			//System.out.print("left:"); printArray(left);
		Integer [] right = new Integer [list.length - middle];
		for (int x = 0; x < list.length - middle; x++) 
			right[x] = list[middle + x];
			//System.out.print("right:"); printArray(right);
		return merge(mergeSort(left), mergeSort(right));
	}
	
	/**
	 * This function merges two already sorted lists (in ascending order)
	 * Approach:
	 * 	- just keeps two pointers and moves pointer along for whichever
	 * list is being used to create the new list
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static Integer [] merge(Integer [] list1, Integer [] list2) {
		int ptr1 = 0;
		int ptr2 = 0;
		int i = 0;
		Integer [] combined = new Integer [list1.length + list2.length];
		/*System.out.println("Combining ->");
			System.out.print("list 1:"); printArray(list1);
			System.out.print("list 2:"); printArray(list2);*/
		while (ptr1 < list1.length || ptr2 < list2.length) {
			if (ptr1 >= list1.length) {
				combined[i] = list2[ptr2];
				ptr2++;
			} else if (ptr2 >= list2.length || list1[ptr1] < list2[ptr2]) {
				combined[i] = list1[ptr1];
				ptr1++;
			} else {
				combined[i] = list2[ptr2];
				ptr2++;
			}
			i++;
		}
		
		/*System.out.println("\tCombined =>");
			System.out.print("combined:"); printArray(combined);*/
		return combined;
	}
	
	/**
	 * A famous problem that returns the maximum subarray (contiguous elements)
	 * in an array 
	 *  	- solved using Kadane's algorithm
	 * @param list
	 * @return
	 */
	public static Integer [] maxSubarray(Integer [] list) {
		int maxSoFar = 0;
		int maxToHere = 0;
		int l = 0;
		int r = 0;
		int temp = 0;
		for (int a = 0; a < list.length; a++) {
			if (maxToHere < 0) {
				maxToHere = list[a];
				temp = a;
			} else {
				maxToHere += list[a];
			}
			
			if (maxToHere >= maxSoFar) {
				maxSoFar = maxToHere;
				l = temp;
				r = a;
			}
		}
		System.out.println((l) + " " + (r) + " max: " + maxSoFar);
		return Arrays.copyOfRange(list, l, r);
	}
	
	/**
	 * Rotates an array by n positions
	 * @param list
	 * @param n
	 * @return
	 */
	public static void rotateArray(Integer [] list, int n) {
		for (int k=0; k < list.length/n; k++) {
			for (int i = 0; i < n; i++) {
				int temp = list[i+k*n];
				list[i+k*n] = list[list.length-n+i];
				list[list.length-n+i] = temp;
			}
		}
	}
	
	/**
	 * Rotates an array by n positions
	 * @param list
	 * @param n
	 * @return
	 */
	public static void rotateArray(Character [] list, int k) {

		int n = k % list.length;
		int fromPos;
		char temp;
		if (n > list.length / 2)
			fromPos = list.length - n;
		else 
			fromPos = 0;
		temp = list[fromPos];
		char temp2 = 'z';
		for (int count = 0; count < list.length; count++) {
			int toPos = n + fromPos;
				toPos = (toPos > list.length - 1) ? toPos - list.length : toPos;
			if (count != 0 && list[toPos] == temp2) {
				toPos += 1;
				fromPos = toPos;
				count --;
				temp = list[fromPos];
			} else {
				temp2 = list[toPos];
				list[toPos] = temp;
				fromPos = toPos;
				temp = temp2;
			}
		}
	}
	
	/**
	 * Reverses an array (Same implementation as string reversal
	 * @param list
	 */
	public static void reverseArray(Character [] list) {
		reverseArrayFromToPosition(list, 0, list.length - 1);
	}
	
	public static void reverseArrayFromToPosition(Character [] list, int fromPos, int toPos) {
		//System.out.println("from Pos: " + fromPos + " to Pos" + toPos);
		int numIterations = (int) Math.ceil((toPos - fromPos) / 2.0);
		for (int x = fromPos; x < fromPos+numIterations; x++) {
			char temp = list[x];
			list[x] = list[toPos+fromPos-x];
			list[toPos+fromPos-x] = temp;
		}
	}
	
	/**
	 * Rotates array using classic three reversals
	 * @param list
	 * @param k
	 */
	public static void rotateArrayReversal(Character [] list, int k) {
		int n = k % list.length;
		reverseArrayFromToPosition(list, 0, list.length - 1);
		reverseArrayFromToPosition(list, 0, n-1);
		reverseArrayFromToPosition(list, n, list.length - 1);
	}
	
	public static boolean areEqual(Character [] list1, Character [] list2) {
		if (list1.length != list2.length)
			return false;
		for (int x = 0; x < list1.length; x++) {
			if (list1[x] != list2[x]) {
				System.out.println(x + ": list1: " + list1[x] + " list2: " + list2[x]);
				return false;
			}
		}
		return true;
	}
}
