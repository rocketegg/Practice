package tests;

import junit.framework.TestCase;
import main.util.ArrayUtil;

import org.junit.Before;

public class ArrayUtilTest extends TestCase {
	int [] array = new int[10];
	int [] array2 = new int[10];
	@Before
	public void setUp() {
		for (int x = 0; x < array.length; x++) {
			array[x] = array.length- 1 -x;
		}
		for (int x = 0; x < array2.length; x++) {
			array2[x] = x;
		}
		array2[5]=9;
	}
	
	public void testDouble() {
		//ArrayUtil.printArray(array);
		System.out.println("Array has double? " + ArrayUtil.hasDouble(array));
		//ArrayUtil.printArray(array);
		System.out.println("Array2 has double? " + ArrayUtil.hasDouble(array2));
	}
	
}
