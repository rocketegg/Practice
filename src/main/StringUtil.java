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
}
