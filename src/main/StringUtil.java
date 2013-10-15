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
