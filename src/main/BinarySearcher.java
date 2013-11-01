package main;

public class BinarySearcher {

	public static <T extends Comparable<T>> int BinarySearch (T[] list, T target) {
		return BinarySearch(list, target, 0, list.length-1);
	}
	
	private static <T extends Comparable<T>> int BinarySearch(T[] list, T target, int low, int high) {
		if (low > high || high < low) {
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
	
	private static <T extends Comparable<T>> int BinarySearchIndex(T[] list, T target, int low, int high) {
		if (low > high || high < low) {
			return -1;	//find closest index and return it
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
}
