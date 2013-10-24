package tests;

import java.util.Arrays;

import junit.framework.TestCase;
import main.util.ArrayUtil;

import org.junit.Before;

public class ArrayUtilTest extends TestCase {
	int [] array = new int[10];
	int [] array2 = new int[10];
	@Before
	public void setUp() {
		for (int x = 0; x < array.length; x++) {
			//array[x] = array.length- 1 -x;
			array[x] = TestUtils.randomInt(0, 9);
		}
		for (int x = 0; x < array2.length; x++) {
			//array2[x] = x;
			array2[x] = TestUtils.randomInt(0, 9);
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
	
	public void testAddArray() {
		System.out.println("TEST ADD ARRAY");
		
		for (int x = 0; x < 100; x++) {
		setUp();
		//ArrayUtil.printArray(array);
		//ArrayUtil.printArray(array2);
		
		//System.out.println("ADDED:");
		int [] array3 = ArrayUtil.addArrays(array, array2);
		//ArrayUtil.printArray(array3);
		
		String a1 = ArrayUtil.arrayValsToString(array);
		String a2 = ArrayUtil.arrayValsToString(array2);
		String a3 = ArrayUtil.arrayValsToString(array3);
		Long sum = Long.parseLong(a1) + Long.parseLong(a2);
		System.out.println("added: " + a3 + " sum: " + sum);
		assertTrue(sum == Long.parseLong(a3));
		}
	}
	
}
