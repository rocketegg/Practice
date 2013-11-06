package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import main.BinarySearcher;
import main.util.ArrayUtil;

import org.junit.Test;

public class BinarySearchTest extends TestCase {

	private Integer[] list_inc;
	private Integer[] list_by_threes;
	
	protected void setUp() {
		list_inc = new Integer[10];
		for (int i = 0; i < list_inc.length; i++) {
			list_inc[i] = i;
		}
		list_by_threes = new Integer[10];
		for (int i = 0; i < list_by_threes.length; i++) {
			list_by_threes[i] = i*3;
		}
	}
	
	@Test
	public void testFringe() {
		assertTrue(BinarySearcher.BinarySearch(list_inc, 0) != -1);
		assertTrue(BinarySearcher.BinarySearch(list_inc, list_inc[list_inc.length - 1]) != -1);
		
		assertTrue(BinarySearcher.BinarySearch(list_by_threes, 0) != -1);
		assertTrue(BinarySearcher.BinarySearch(list_by_threes, list_by_threes[list_inc.length - 1]) != -1);
	}
	
	@Test
	public void testOutOfBounds() {
		for (int x = 0; x < 100; x++) {
			int target_inc = list_inc[Math.round((float) (Math.random()*100)) % (list_inc.length-1)] * -1 -1 ;
			System.out.println("TestOutOfBounds: " + target_inc);
			assertTrue(BinarySearcher.BinarySearch(list_inc, target_inc) == -1);
			assertTrue(BinarySearcher.BinarySearch(list_by_threes, list_by_threes[Math.round((float) (Math.random()*100)) % (list_by_threes.length-1)] - 1) == -1);
		}
	}
	
	@Test
	public void testNormal() {
		for (int x = 0; x < 100; x++) {
			assertTrue(BinarySearcher.BinarySearch(list_inc, list_inc[Math.round((float) (Math.random()*100)) % (list_inc.length-1)]) != -1);
			assertTrue(BinarySearcher.BinarySearch(list_by_threes, list_by_threes[Math.round((float) (Math.random()*100)) % (list_by_threes.length-1)]) != -1);
		}
	}
	
	@Test
	public void testBinarySearchRotate() {
		Integer [] temp = list_by_threes.clone();
		ArrayUtil.printArray(temp);
		ArrayUtil.rotateArray(temp, 3);
		ArrayUtil.printArray(temp);
		
		System.out.println(BinarySearcher.binarySearchRotated(temp, 9));
		System.out.println(BinarySearcher.binarySearchRotated(temp, 24));
		//System.out.println(BinarySearcher.binarySearchRotated(temp, 27));
		//System.out.println(BinarySearcher.binarySearchRotated(temp, 28));
		System.out.println(BinarySearcher.binarySearchRotated(temp, 21));
		System.out.println(BinarySearcher.binarySearchRotated(temp, 22));
	}

}
