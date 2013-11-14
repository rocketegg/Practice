package tests;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import org.junit.Before;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import main.util.ArrayUtil;
import main.util.WriterUtil;
import junit.framework.TestCase;

public class WriterUtilTest extends TestCase {
	int range = 10000000;
	int k = 5000000;
	String filename = "unique_integer_list.txt";
	Integer [] array;
	
	@Before
	public void setUp() {
		try {
			array = TestUtils.randomUniqueArray(range, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testWriteNList() {
		//WriterUtil.writeNList(range, k, filename);
		writeArrayBitSet();
		writeArrayMergeSort();
	}
	
	public void writeArrayMergeSort() {
		try {
			System.out.println("MERGESORT");
			System.out.println("Range: " + range + "  K: " + k);
			List<Integer> list = Arrays.asList(array);
			long startTime = System.currentTimeMillis();
			System.out.println("\tStart Time: " + startTime);
			//ArrayUtil.mergeSort(array);
			Collections.sort(list);
			//WriterUtil.writeArray(ArrayUtil.mergeSort(array), "unique_integer_list_mergeSort.txt");
			long endTime = System.currentTimeMillis();
			double duration = (endTime - startTime);
			System.out.println("Total Time: " + duration + " milliseconds");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeArrayBitSet() {
		System.out.println("BITSET");
		System.out.println("Range: " + range + "  K: " + k);
		long startTime = System.currentTimeMillis();
		System.out.println("\tStart Time: " + startTime);
		BitSet b = new BitSet(k);
		for (int x = 0; x < array.length; x++) {
			b.set(array[x]);
		}
		//WriterUtil.writeArray(b, "unique_integer_list_bitset.txt");
		long endTime = System.currentTimeMillis();
		double duration = (endTime - startTime);
		System.out.println("Total Time: " + duration + " milliseconds");
	}
}
