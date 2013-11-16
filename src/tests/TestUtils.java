package tests;

public class TestUtils {

	/**
	 * generates random int from low to high
	 * @param low
	 * @param high
	 * @return
	 */
	public static int randomInt(int low, int high) {
		int random = Math.round((float) (Math.random()*10000000));
		int range;
		if (low < 0 && high < 0) {
			range = Math.abs(high + low);
		} else if (low <0 && high >= 0) {
			range = high - low;
		} else if (high <0 && low >= 0) {
			range = low - high;
		} else {
			range = high - low;
		}
		int modabs = (random) % range;
		int value = low + modabs;

		//System.out.println("\tRand: " + random + "\tRange: " + range + "\tModabs: " + modabs + "\tValue: " + value) ;
		return value;
	}
	
	/**
	 * generates random string of length (ascii alphabetical & numeric)
	 * @param length
	 * @return
	 */
	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < length; x++) {
			if (randomInt(0,1) == 1) {
				sb.append((char)randomInt(48, 90));
			} else {
				sb.append((char)randomInt(97,122));
			}
		}
		return sb.toString();
	}
	
	/**
	 * generates random substring of s
	 * @param s
	 * @return
	 */
	public static String randomSubstring(String s) {
		if (s.length() < 2)
			return s;
		int range1 = 0;
		int range2 = 0;
		while (range1 == range2) {
			range1 = randomInt(0, s.length()-1);
			range2 = randomInt(0, s.length()-1);
			if (range1 > range2) {
				return s.substring(range2, range1);
			} else if (range2 > range1) {
				return s.substring(range1, range2);
			} 
		}
		return s;
	}
	
	/**
	 * Generates an array of size k of unique values from 0 to range
	 * 	Range must be > k
	 * @param range
	 * @param k
	 * @return
	 * @throws Exception 
	 */
	public static Integer [] randomUniqueArray(int range, int k) throws Exception {
		if (range < k) throw new Exception();
		
		Integer [] returnArray = new Integer [range];
		for (int i = 0; i < range; i++) {
			returnArray[i] = i;
		}
		
		int l = 0;
		int r = 0;
		int temp = 0;
		for (int i = 0; i < range; i++) {
			l = randomInt(0, range);
			r = randomInt(0, range);
			temp = returnArray[l];
			returnArray[l] = returnArray[r];
			returnArray[r] = temp;
		}
		
		Integer [] truncArray = new Integer[k];
		for (int i = 0; i < k; i++) {
			truncArray[i] = returnArray[i];
		}
		return truncArray;
	}

	
}
