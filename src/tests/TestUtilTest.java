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
		int tries = 1000000;
		int s = 8;
		for (int x = 0; x < tries; x++) {
			
			do {
				s = getThree();
				if (sIsValid(s,x)) {
					array[sIndex(s,x)]++;
				}
			} while (!sIsValid(s,x));
		}
		
		for (int x = 0; x < 6;x ++) {
			System.out.println(x + ": " + array[x] + "  " + new Double(array[x]*100.0/tries) + "percent");
		}
		
	}
	
	private int getThree() {
		int rand1 = TestUtils.randomInt(0,TestUtils.randomInt(40000,80000)) % 2;
		int rand2 = TestUtils.randomInt(0,TestUtils.randomInt(40000,80000)) % 2;
		int rand3 = TestUtils.randomInt(0,TestUtils.randomInt(40000,80000)) % 2;
		byte b = (byte) ((rand1 << 2) + (rand2 << 1) + rand3);
		//System.out.println(b);
		return new Integer(b);
	}
	
	//rotates which 2 are invalid, start with 6,7, then 7,1, then 1,2, 2,3 etc.
	private boolean sIsValid(int s, int attempt) {
		int rotation = attempt % 8;
		int inv1 = (6+rotation) % 8;	//if rotation = 0, inv1 = 6, if rotation = 1, inv1 = 7, if rotation = 2, inv = 1, etc.
		int inv2 = (6+rotation+1) % 8;
		return s != inv1 && s != inv2;
	}
	
	private int sIndex(int s, int attempt) {
		int rotation = attempt % 8;
		int inv1 = (6+rotation) % 8;	
		int inv2 = (6+rotation+1) % 8;
	
		if (s > inv2 && s < inv1) { // I2 1 2 3 4 5 6 I1
			return s - 1;
		} else if (s > inv2) {	// 0 1 2 I1 I2 5 6 7
			return s - 2;
		} else {	// 0 1 2 3 4 5 I1 I2
			return s;
		}
	}
}
