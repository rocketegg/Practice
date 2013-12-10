package main.adt;

import main.adt.element.Entry;

public interface PriorityQueue<K extends Comparable<K>, V> {

	/**
	 * Returns the top element of the priority queue (without removal)
	 * @return
	 */
	public Entry<K,V> returnTop();
	
	/**
	 * Insert an entry into the priority queue
	 * @param entry
	 */
	public void insert(Entry<K,V> entry);
	
	/**
	 * Removes the top element in the priority queue
	 * @return
	 */
	public Entry<K,V> removeTop();
}
