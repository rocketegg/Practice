package main;

public class StringUtil {

	/**
	 * converts ascii String s into int
	 * @param s
	 * @return
	 */
	public static int atoi(String s) {
		int i = 0;
		char[]c = s.toCharArray();
		boolean neg = c[0] == '-';
		for (int x = (neg) ? 1 : 0; x < c.length; x++){
			i *= 10;
			i += c[x] - 48;
		}
		return (neg) ? (i * -1) : i;
	}
	
	/**
	 * Reverses the order of words in a string in place (w/o extra memory)
	 * E.g. "the cat came home" = "home came cat the"
	 *   - naive approach would be to break words apart by string.split, then 
	 *     compose the string in reverse order.
	 *   - in place, reverse all the characters, then go through string again
	 *     everywhere you find a word, reverse all characters there
	 *     
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		char[]c = reverseWord(s).toCharArray();
		int wordLength = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ' || i == c.length-1) {
				//reverse
				for (int x = i-wordLength, y = 0; x < i-wordLength/2; x++, y++) {
					char temp = c[x];
					char swap = (i == c.length -1) ? c[i-y] : c[i-1-y];
					c[x] = swap;
					if (i == c.length -1) {
						c[i-y] = temp;
					} else {
						c[i-1-y] = temp;
					}
				}
				wordLength=0;
			} else {
				wordLength++;
			}
		}
		System.out.println("reversed: " + new String(c));
		return new String(c);
		
	}
	
	private static String reverseWord(String s) {
		char[]c = s.toCharArray();
		for (int x = 0; x < c.length/2; x++) {
			char temp = c[x];
			c[x] = c[c.length-1-x];
			c[c.length-1-x] = temp;
		}
		//System.out.println("Reversed:" + c);
		return new String(c);
	}
	
	 /**
	 * Function that determines whether string s1 is a substring of string s2
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isSubstring(String s1, String s2) {
		int s1_ptr = 0;
		int s2_ptr = 0;
		while (s2_ptr < s2.length()) {
			if (s2.charAt(s2_ptr) == s1.charAt(s1_ptr)) {
				s1_ptr++;
				s2_ptr++;
				if (s1_ptr >= s1.length()) {	
					return true;	//reached end of s1 string
				}
			} else if (s1_ptr > 0){
				s1_ptr = 0;
			} else {
				s2_ptr++;
			}
		}	
		return false;	//reached end of s2 string
		
	}

}
