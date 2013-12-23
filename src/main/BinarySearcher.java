package main;

public class BinarySearcher {

	/**
	 * Searches for a target T in a list of Ts
	 * @param list
	 * @param target
	 * @return index of value (other -1)
	 */
	public static <T extends Comparable<T>> int BinarySearch (T[] list, T target) {
		return BinarySearch(list, target, 0, list.length-1);
	}
	
	private static <T extends Comparable<T>> int BinarySearch(T[] list, T target, int low, int high) {
		if (low > high || high < low) {
			assert((high < 0 || list[high].compareTo(target) < 0) && (high+1 >= list.length || list[high].compareTo(target) > 0));
			return -1;
		}
		int index = (low + high) / 2;
		if(list[index].equals(target)) {
			//System.out.println("Found " + target + "at index: " + index);
			
			return index;
		} else if (list[index].compareTo(target) < 0) {	//move up half
			return BinarySearch(list, target, index+1, high);
		} else {
			return BinarySearch(list, target, low, index-1);
		}
	}
	
//	private static <T extends Comparable<T>> int BinarySearchIndex(T[] list, T target, int low, int high) {
//		if (low > high || high < low) {
//			return -1;	//find closest index and return it
//		}
//		int index = (low + high) / 2;
//		if(list[index].equals(target)) {
//			//System.out.println("Found " + target + "at index: " + index);
//			return index;
//		} else if (list[index].compareTo(target) < 0) {	//move up half
//			return BinarySearch(list, target, index+1, high);
//		} else {
//			return BinarySearch(list, target, low, index-1);
//		}
//	}
//	
	public static int binarySearchRotated(Integer[] temp, int target) {
		System.out.println("Target: " + target);
		return binarySearchRotated(temp, target, 0, temp.length-1);
	}
	
	private static int binarySearchRotated(Integer[]list, int target, int low, int high) {
		if (low > high || high < low) {
			return -1;	//find closest index and return it
		}
		int index = (low + high) / 2;
		int first = list[0];
		int last = list[list.length-1];
		if (list[index] == target) {
			System.out.println("Found " + target + "at index: " + index);
			return index;
		} else if (list[index] < first) { //this means we're in the (second) sorted part of the array
			if (list[index] < target && target <= last) {	//if the target belongs in this half, just do binary search
				return BinarySearch(list, target, index+1, high);
			} else {	//otherwise, we have to look in the unsorted rotated portion
				return binarySearchRotated(list, target, low, index-1);
			}
		} else { //this means we're in the first half of the array, which is still sorted
			if (list[index] > target && target >= first) {	//if the target belongs in the first half, just do binary search
				return BinarySearch(list, target, low, index-1);
			} else {	//otherwise, we have to look in the unsorted rotated portion
				return binarySearchRotated(list, target, index+1, high);
			}
		}
	}
}
