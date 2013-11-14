package main.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import tests.TestUtils;

public class WriterUtil {

	/**
	 * Writes out n unique integers to a file of filename
	 * @param range
	 * @param k
	 */
	public static void writeNList(int range, int k, String filename) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			Integer [] array = TestUtils.randomUniqueArray(range, k);
			for (int x = 0; x < array.length; x++) {
				out.println(array[x]);
			}
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeArray(Integer [] array, String filename) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for (int x = 0; x < array.length; x++) {
				out.println(array[x]);
			}
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeArray(BitSet b, String filename) {
		// TODO Auto-generated method stub
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for (int x = 0; x < b.length(); x ++) {
				if (b.get(x) == true)
					out.println(x);
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
