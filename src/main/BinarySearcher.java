package main;

public class BinarySearcher {

	public static int BinarySearch(int[] list, int target) {
		return BinarySearch(list, target, 0, list.length-1);
	}
	
	private static int BinarySearch(int[] list, int target, int low, int high) {
		if (low > high || high < low) {
			return -1;
		}
		int index = (low + high) / 2;
		if(list[index] == target) {
			//System.out.println("Found " + target + "at index: " + index);
			return index;
		} else if (list[index] < target) {	//move up half
			return BinarySearch(list, target, index+1, high);
		} else {
			return BinarySearch(list, target, low, index-1);
		}
	}
}
