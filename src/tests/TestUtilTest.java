package tests;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUtilTest extends TestCase {

	@Test
	public void testRandomInt() {
		int low = -50;
		int high = 50;
		for (int x = low; x < high; x++) {
			int random = TestUtils.randomInt(x, high);
			System.out.println("[" + x + " to " + high + "]: " + random);
			assertTrue(random <= high && random >= x);
		}
	}
	
	/**
	 * 
	 */
	public void testRandom() {
		int[] array = new int[6];
		for (int x = 0; x < 1000000; x++) {
			int rand1 = TestUtils.randomInt(0,100) % 2+1;
			int rand2 = TestUtils.randomInt(0,100) % 2+1;
			int rand3 = TestUtils.randomInt(0,100) % 2+1;
			String s = new Integer(rand1).toString() + new Integer(rand2).toString() + new Integer(rand3).toString();
			System.out.println(s);
			switch(s) {
				case "111":
					array[0]++;
					break;
				case "112":
					array[1]++;
					break;
				case "121":
					array[2]++;
					break;
				case "122":
					array[3]++;
					break;
				case "211":
					array[4]++;
					break;
				case "212":
					array[5]++;
					break;
				default:
					break;
			}
		}
		
		for (int x = 0; x < 6;x ++) {
			System.out.println(x + ": " + array[x] + "  " + new Double(array[x]/10000) + "percent");
		}
		
	}
}
