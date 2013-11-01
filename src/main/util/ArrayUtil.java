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
	public static int [] mergeSort(int [] list) {
		if (list.length == 1)
			return list;
		int middle = list.length/2;
		int [] left = new int [middle];
		for (int x = 0; x < middle; x++) 
			left[x] = list[x];
			System.out.print("left:"); printArray(left);
		int [] right = new int [list.length - middle];
		for (int x = 0; x < list.length - middle; x++) 
			right[x] = list[middle + x];
			System.out.print("right:"); printArray(right);
		return merge(mergeSort(left), mergeSort(right));
	}
	
	/**
	 * This function merges two already sorted lists (in ascending order)
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static int [] merge(int [] list1, int [] list2) {
		int ptr1 = 0;
		int ptr2 = 0;
		int i = 0;
		int [] combined = new int [list1.length + list2.length];
		System.out.println("Combining ->");
			System.out.print("list 1:"); printArray(list1);
			System.out.print("list 2:"); printArray(list2);
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
		
		System.out.println("\tCombined =>");
			System.out.print("combined:"); printArray(combined);
		return combined;
	}
	
}
