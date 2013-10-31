package main.util;

import java.util.HashMap;

public class LinkedList <T> {

	public Node<T> head;
	
	public LinkedList() {
		this.head = null;
	}
	
	public LinkedList(Node<T> n) {
		this.head = n;
	}
	
	public void insert(T n) {
		this.insert(new Node<T>(n));
	}
	
	public void insert(Node<T> n) {
		Node<T> t = head;
		if (t == null) {
			head = n;
		} else {
			while (t.next != null) {
				t = t.next;
			}
			t.next = n;
		}
	}
	
	public void print() {
		Node<T> t = this.head;
		while (t != null) {
			System.out.print(t.value + " -> ");
			t = t.next;
		}
		System.out.print("null\n");
	}
	
	public LinkedList<T> combineLists(LinkedList<T> list2) {
	    Node<T> ptr1 = this.head;
	    Node<T> ptr2 = list2.head;
	    
	    Node<T> newList = new Node<T>();
	    Node<T> t = newList;
	    
	    while (ptr1 != null) {
	        T val = ptr1.value;
	        T val2 = ptr2 != null ? ptr2.value : null;
	        while (ptr2 != null && ptr2.compareTo(ptr1) == 1) {
	            t.next = new Node<T>(val2);
	            t = t.next;
	            ptr2 = ptr2.next;
	            val2 = ptr2 != null ? ptr2.value : null;
	        }
	        t.next = new Node<T>(val);
	        t = t.next;
	        ptr1 = ptr1.next;
	    }

	    if (ptr2 != null) {
	    	t.next = new Node<T>(ptr2.value);
	    	t = t.next;
	    	ptr2 = ptr2.next;
	    }

	    //newList = newList.next;
	    return new LinkedList<T>(newList.next);
	}
	
	/**
	 * another implementation combining two sorted lists
	 * @return
	 */
	public LinkedList<T> merge(LinkedList<T> list2) {
		Node<T> temp = new Node<T>();
		Node<T> ptr = temp;
		Node<T> l1 = this.head;
		Node<T> l2 = list2.head;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				ptr.next = new Node<T>(l2.value);
				l2 = l2.next;
			} else if (l2 == null) {
				ptr.next = new Node<T>(l1.value);
				l1 = l1.next;
			} else if (l1.compareTo(l2) == 1) {
				ptr.next = new Node<T> (l1.value);
				l1 = l1.next;
			} else {
				ptr.next = new Node<T> (l2.value);
				l2 = l2.next;
			}
			ptr = ptr.next;
		}
		return new LinkedList<T>(temp.next);
	}
	
	/**
	 * Takes a linked list and removes duplicates and returns a new list
	 * @return
	 */
	public LinkedList<T> removeDuplicatesHashMap() {
		Node<T> list = this.head;
		Node<T> ret = this.head;
		HashMap<T, Boolean> values = new HashMap<T, Boolean>();
		
		while (list != null) {
			Node<T> prev = list;
			if (!values.containsKey(list.value))
				values.put(list.value, true);
			list = list.next;
			if (list != null) {
				if (values.containsKey(list.value)) { 
					prev.next = list.next;
					list = prev;
				}
			} 
		}
		return new LinkedList<T>(ret);
	}
	
	public LinkedList<T> removeDuplicatesConstantMemory() {
		return null;
	}
	
	/**
	 * returns the linked list in reverse
	 * @return
	 */
	public LinkedList<T> reverseIterative() {
		Node<T> curr = this.head;
		if (curr == null || curr.next == null) //if one element
			return new LinkedList<T>(curr);
		else {	//more than one
			Node<T> prev = curr;
			curr = curr.next;
			prev.next = null;
			while (curr != null) {
				Node<T> temp = curr.next;
				curr.next = prev;
				prev = curr;
				curr = temp;
			}
			this.head = prev;
			LinkedList<T> returnVal = new LinkedList<T>(prev);
			return returnVal;
		}
		
	}
	
}
