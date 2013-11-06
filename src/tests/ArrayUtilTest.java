package tests;

import junit.framework.TestCase;
import main.util.ArrayUtil;

import org.junit.Before;

public class ArrayUtilTest extends TestCase {
	Integer [] array = new Integer[15];
	Integer [] array2 = new Integer[15];
	@Before
	public void setUp() {
		for (int x = 0; x < array.length; x++) {
			//array[x] = array.length- 1 -x;
			array[x] = TestUtils.randomInt(0, 50);
		}
		for (int x = 0; x < array2.length; x++) {
			//array2[x] = x;
			array2[x] = TestUtils.randomInt(0, 20);
		}
		//array2[5]=9;
	}
	/*
	public void testDouble() {
		//ArrayUtil.printArray(array);
		System.out.println("Array has double? " + ArrayUtil.hasDouble(array));
		//ArrayUtil.printArray(array);
		System.out.println("Array2 has double? " + ArrayUtil.hasDouble(array2));
	}*/
	
	/*
	public void testAddArray() {
		System.out.println("TEST ADD ARRAY");
		for (int x = 0; x < 100; x++) {
			setUp();
			int [] array3 = ArrayUtil.addArrays(array, array2);
			String a1 = ArrayUtil.arrayValsToString(array);
			String a2 = ArrayUtil.arrayValsToString(array2);
			String a3 = ArrayUtil.arrayValsToString(array3);
			Long sum = Long.parseLong(a1) + Long.parseLong(a2);
			System.out.println("added: " + a3 + " sum: " + sum);
			assertTrue(sum == Long.parseLong(a3));
		}
	}*/
	
	/*
	public void testMerge() {
		for (int x = 0; x < array.length; x++) {
			array[x] = x;
		}
		for (int x = 0; x < array2.length; x++) {
			array2[x] = 2*x;
		}
		ArrayUtil.printArray(array);
		ArrayUtil.printArray(array2);
		ArrayUtil.printArray(ArrayUtil.merge(array, array2));
	}*/
	/*
	public void testMergeSort() {
		System.out.print("Testing merge sort on array: "); ArrayUtil.printArray(array);
		System.out.println("=======================================");
		ArrayUtil.mergeSort(array);
	}*/
	
	/*
	public void testMaxSubArray() {
		System.out.println("Testing max sub array");
		System.out.println("=======================================");
		
		ArrayUtil.printArray(array2);
		Integer[] maxSub = ArrayUtil.maxSubarray(array2);
		ArrayUtil.printArray(maxSub);
		
	}*/
	
	public void testRotate() {
		array2 = ArrayUtil.mergeSort(array2);
		ArrayUtil.printArray(array2);
		ArrayUtil.rotateArray(array2, 5);
		ArrayUtil.printArray(array2);
		ArrayUtil.rotateArray(array2, 1);
		ArrayUtil.printArray(array2);
	}
	
}
