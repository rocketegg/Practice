package main;

import java.util.Collection;
import java.util.HashSet;


public class Iterator<T>{

	private HashSet<T> hashSet;
	
	//Default constructor
	public Iterator () {
		hashSet = new HashSet<T>();
	}
	
	public boolean addElement(T t) {
		return hashSet.add(t);
	}
	
	/**
	 * Iterates over the hashSet to determine if an object exists there
	 * @param t
	 * @return
	 */
	public boolean hasElement(T t) {
		java.util.Iterator<T> i = hashSet.iterator();
		
		while (i.hasNext()) {
			T element = (T) i.next();
			System.out.print(element.toString() + " ");
			if (element.equals(t)) {
				System.out.println();	//Note that hashSet does not store these in order.
				return true;
			}
				
		}
		return false;
	}
	
	/**
	 * Iterates over the hashSet to delete an element if it exists
	 * @param t
	 * @return
	 */
	public boolean deleteElement(T t) {
		java.util.Iterator<T> i = hashSet.iterator();
		
		while (i.hasNext()) {
			T element = (T) i.next();
			System.out.print(element.toString() + " ");
			if (element.equals(t)) {
				System.out.println();	//Note that hashSet does not store these in order.
				i.remove();		//Delete element
				return true;
			}
		}
		return false;
	}
	
}
