package main.adt;

import main.adt.element.Entry;

import org.junit.Test;

import junit.framework.TestCase;

public class BinaryMinHeap<K extends Comparable<K>, V> 
	implements PriorityQueue<K, V> {

	private Entry<K,V> [] array;
	private int size;	//number of entries in the heap
	private final static double SIZE_FACTOR = 1.5;
	
	public BinaryMinHeap (int size) {
		//
		array = new Entry [(int) (size * SIZE_FACTOR)];
		for (int x = 0; x < array.length; x++) {
			array[x] = null;
		}
		this.size = 0;
	}
	
	public BinaryMinHeap(Entry<K,V>[] arrayOfEntries) {
		this.size = 0;
		heapify(arrayOfEntries);
	}
	
	private int height(int n) {
		if (n <= 1) {
			return 1;
		}
		return 1 + height(n/2);
	}

	@Override
	public Entry<K, V> returnTop() {
		return array[1];
	}

	@Override
	public void insert(Entry<K, V> entry) {
		if (size > array.length / 2) {		
			increaseArraySize();
		}
		int insertIndex = size+1;
		array[insertIndex] = entry;
		size++;
		bubbleUp(insertIndex/2, insertIndex);
	}

	@Override
	public Entry<K, V> removeTop() {
		Entry<K,V> returnEntry = returnTop();
		heapifyOne();
		size--;
		return returnEntry;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Takes last element and puts at top, then bubbles down
	 * @param i
	 * @param j
	 */
	private void heapifyOne() {
		array[1] = array[size];
		array[size] = null;
		bubbleDown(1);
	}
	
	/**Takes an array of entries and heapifies all
	 * 
	 */
	private void heapify(Entry<K, V> [] arrayOfEntries) {
		array = new Entry [(int) (arrayOfEntries.length * SIZE_FACTOR)];
		for (int x = 0; x < arrayOfEntries.length; x++) {
			array[x+1] = arrayOfEntries[x];
		}
		
		size = arrayOfEntries.length;

		for (int x = arrayOfEntries.length; x < array.length - 1; x++) {
			array[x+1] = null;
		}
		
		for (int y = size/2; y >= 1; y--) {
			bubbleDown(y);
		}
	}
	
	/**
	 * Copies array over to new bigger array
	 */
	private void increaseArraySize() {
		//System.out.println("Using " + size * 100 /array.length + " percent of total heap size");
		int newsize = (int) (array.length * SIZE_FACTOR);
		System.out.println("Increasing total array size to: " + newsize);
		Entry<K,V> [] newArray = new Entry [newsize];
		for (int x = 0; x < newsize; x++) {
			if (x >= 1 && x < size+1)
				newArray[x] = array[x];
			else
				newArray[x] = null;
		}
		array = newArray;
	}
	
	/**
	 * Takes top element and bubbles down
	 */
	private void bubbleDown(int i) {
		int j = 2 * i;
		int k = 2 * i + 1;
		if (j <= array.length - 1 && array[j] != null) {
			if (array[k] != null) {
				int min = minEntry(i, j, k);
				if (i != min) {
					swap(i, min);
					bubbleDown(min);
					//assertTrue("heap is valid from position: " + i, isValidHeap(i));
				}
			} else {
				if (array[j].compareTo(array[i]) > 0) {
					swap(i, j);
					//assertTrue("heap is valid from position: " + i, isValidHeap(i));
				}
			}
		}
	}
	
	private void assertTrue(String string, boolean condition) {
		if (!condition) {
			System.out.println("{ASSERT TRUE: " + string + " = " + condition + "}");
			System.out.println("\tHeap size: " + size);
			printHeapKeys();
			System.exit(0);
		}
	}

	private int minEntry(int i, int j, int k) {
		int min = i;
		//assertTrue("array[i] should not null, Index @ " + i, array[i] != null);
		if (array[min].compareTo(array[j]) < 0)
			min = j;
		if (array[min].compareTo(array[k]) < 0)
			min = k;
		return min;
	}
	
	/**
	 * Bubbles up last element
	 */
	private void bubbleUp(int i, int j) {
		if (i > 0 && array[i].compareTo(array[j]) <= 0) {
			swap(i,j);
			bubbleUp(i/2,i);
		}
	}
	
	private void swap(int i, int j) {
		Entry<K,V> temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public void printHeapKeys() {
		System.out.print("Heap Keys: [ ");
		for (int i = 1; i < size+1; i++) {
			//assertTrue("Print Heap Keys, array[i] is not null: " + i, array[i] != null);
			System.out.print(array[i].getKey() + " " );
		}
		System.out.print("]");
	}
	
	public void printHeapValues() {
		System.out.print("Heap Values: [ ");
		for (int i = 1; i < size+1; i++) {
			System.out.print(array[i].getValue() + " " );
		}
		System.out.print("]");
	}

	/**
	 * Calculates whether HEAP is valid from position pos
	 * @param pos
	 * @return
	 */
	public boolean isValidHeap(int pos) {
		boolean isValid = true;
		for (int y = pos; y <= (int) Math.pow(2, height(size) - 1) - 1; y++) {
			int parent = y;
			int l = 2 * parent;
			int r = 2 * parent + 1;
			if (array[l] != null && array[r] != null) {
				isValid &= array[parent].compareTo(array[l]) >= 0 && array[parent].compareTo(array[r]) >= 0;
			} else if (array[l] != null && array[r] == null) {
				isValid &= array[parent].compareTo(array[l]) >= 0;
			}
		}
		return isValid;
	}
}
