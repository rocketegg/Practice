package tests;

import junit.framework.TestCase;
import main.util.ArrayUtil;

import org.junit.Before;

public class ArrayUtilTest extends TestCase {
	Integer [] array = new Integer[10];
	Integer [] array2 = new Integer[10];
	@Before
	public void setUp() {
		char a = 'a';
		char what = (char) (a + 1);
		System.out.println("what is:" + what);
		
		
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
	
	public void testBlah() {
		//blah
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
	
	/* public void testRotate() {

		for (int i = 0; i < 100; i++) {
		for (int x = 2; x < 26; x++) {
			Character [] array1 = new Character[x];
			Character [] array2 = new Character[x];
			for (int y = 0; y < array1.length; y++) {
				array1[y] = (char) ((int)'a' + (y % 26));
				array2[y] = (char) ((int)'a' + (y % 26));
			}
			
			int z = TestUtils.randomInt(0, array1.length*2);
				System.out.print("Rotating array [" + z + "] positions.  \nExpect: \t");
				ArrayUtil.rotateArrayReversal(array1, z);
				ArrayUtil.printArray(array1);
				ArrayUtil.rotateArray(array2, z);
				System.out.print("Got: \t\t");
				ArrayUtil.printArray(array2);
				if (ArrayUtil.areEqual(array1, array2)) {
					System.out.println("OK");
				} else {
					System.out.println("ERROR");
				}
		}
		}
		
	}
	*/
	
}
