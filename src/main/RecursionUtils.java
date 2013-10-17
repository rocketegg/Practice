package main;

public class RecursionUtils {

	private static int[][] combinationMatrix;
	
	static {
		combinationMatrix = new int[110][110];
	}
	
	/**
	 * Returns the number of combinations that can be made in an
	 * int of size n with ints up to size m.
	 * 
	 * E.g. getNumCombinations(5, 2)
	 *  2 + 2 + 1 = 5
	 *  2 + 1 + 1 + 1 = 5
	 *  1 + 1 + 1 + 1 + 1 = 5
	 * @param m
	 * @param n
	 * @return
	 */
	private static int numCallsDynamic = 0;
	public static int getNumCombinationsDynamic(int n, int m) {
		numCallsDynamic++;
		if (n >= 0 && m >= 0 && combinationMatrix[n][m] != 0) {
			System.out.println("Found [" + n + "][" + m + "] in matrix: " + combinationMatrix[n][m]);
			return combinationMatrix[n][m];
		} else {
			if (n < 0) {	//if n <= 0, no combinations can be made
				return 0;
			} else if (n == 0 || m == 1) {	//if n is zero, this is the same as n - m, where n = m
				return store(n, m, 1);
			} else {	//Num combinations of n in m parts is # combinations of n in m - 1 parts + n-m in m parts
				return store(n, m, getNumCombinationsDynamic(n, m - 1) + getNumCombinationsDynamic(n-m, m));
			}
		}
	}
	
	/**
	 * Gets num combinations - non dynamic version
	 * @param n
	 * @param m
	 * @return
	 */
	private static int numCallsNonDynamic = 0;
	public static int getNumCombinations(int n, int m) {
		numCallsNonDynamic++;
		if (n < 0) {	//if n <= 0, no combinations can be made
			return 0;
		} else if (n == 0 || m == 1) {	//if n is zero, this is the same as n - m, where n = m
			return 1;
		} else {	//Num combinations of n in m parts is # combinations of n in m - 1 parts + n-m in m parts
			return getNumCombinations(n, m - 1) + getNumCombinations(n-m, m);
		}
	}
	
	private static int store(int n, int m, int value) {
		combinationMatrix[n][m] = value;
		//System.out.println("Storing [" + n + "][" + m + "] in matrix: " + combinationMatrix[n][m]);
		return combinationMatrix[n][m];
	}
	
	public static int getNumCallsDynamic() {
		return numCallsDynamic;
	}
	
	public static int getNumCallsNonDynamic() { 
		return numCallsNonDynamic;
	}
	
}
