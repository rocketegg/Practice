package tests;

import main.adt.BinaryMinHeap;
import main.adt.element.Entry;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class BinaryMinHeapTest extends TestCase {

	BinaryMinHeap<Integer, Integer> myHeap;
	public static final int SIZE = 10;
	
	public void setUpHeapify(int size) {
		//USING INSERT METHOD
//		myHeap = new BinaryMinHeap<Integer, Integer>(SIZE);
//		for (int x = 0; x < SIZE; x++) {
//			Entry<Integer, Integer> e = 
//					new Entry<Integer, Integer>(TestUtils.randomInt(0, 15), x);
//			myHeap.insert(e);
//		}
		//USING HEAPIFY
		Entry<Integer, Integer>[] array = new Entry [size];
		for (int x = 0; x < array.length; x++) {
			array[x] = new Entry<Integer, Integer>(TestUtils.randomInt(0, 10000), x);
			//System.out.print(array[x].getKey() + " " );
		}
		myHeap = new BinaryMinHeap<Integer,Integer>(array);
	}
	
	public void setUpInsert(int size) {
		//USING INSERT METHOD
		myHeap = new BinaryMinHeap<Integer, Integer>(size);
		for (int x = 0; x < size; x++) {
			Entry<Integer, Integer> e = 
					new Entry<Integer, Integer>(TestUtils.randomInt(0, 10000), x);
			myHeap.insert(e);
		}
	}
	
	@Test
	public void testPrint() {
		runTest();
	}
	
	public void runTest() {
		for (int x = 0; x < 10; x ++) {
			long start = System.currentTimeMillis();
			int size = 2500000;//TestUtils.randomInt(100000,200000);
			System.out.println("Test " + x + "\tSize: " + size);
			if (x % 2 == 1) {
				System.out.println("\tHeapify");
				setUpHeapify(size);
			} else {
				System.out.println("\tInsert");
				setUpInsert(size);
			}
			//myHeap.printHeapKeys();
			/*System.out.println("\tTesting removal");
			assertTrue("My Heap is Valid:", myHeap.isValidHeap(1));
			
			while(!myHeap.isEmpty()) {
				Entry<Integer, Integer> removed = myHeap.removeTop();
				//System.out.println("\t Removed: " + removed.getKey());
				assertTrue("Heap is still valid", myHeap.isValidHeap(1));
			}*/
			long end = System.currentTimeMillis();
			long duration = end - start;
			System.out.println("\tRunning Time: " + duration/(1000.0) + " seconds");
		}

	}
	
}
