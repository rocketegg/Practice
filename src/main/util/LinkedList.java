package main.util;

import java.util.HashMap;

public class LinkedList {

	public Node<Integer> head;
	
	public LinkedList() {
		this.head = null;
	}
	
	public LinkedList(Node n) {
		this.head = n;
	}
	
	public void insert(Integer n) {
		this.insert(new Node<Integer>(n));
	}
	
	public void insert(Node n) {
		Node t = head;
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
		Node t = this.head;
		while (t != null) {
			System.out.print(t.value + " -> ");
			t = t.next;
		}
		System.out.print("null\n");
	}
	
	public LinkedList combineLists(LinkedList list2) {
	    Node<Integer> ptr1 = this.head;
	    Node<Integer> ptr2 = list2.head;
	    
	    LinkedList newList = new LinkedList(new Node<Integer>(0));
	    Node t = newList.head;
	    
	    while (ptr1 != null) {
	        int val = ptr1.value;
	        int val2 = ptr2 != null ? ptr2.value : null;
	        while (ptr2 != null && val2 > val) {
	            t.next = new Node<Integer>(val2);
	            t = t.next;
	            ptr2 = ptr2.next;
	            val2 = ptr2 != null ? ptr2.value : null;
	        }
	        t.next = new Node<Integer>(val);
	        t = t.next;
	        ptr1 = ptr1.next;
	    }

	    if (ptr2 != null) {
	    	t.next = new Node<Integer>(ptr2.value);
	    	t = t.next;
	    	ptr2 = ptr2.next;
	    }

	    newList.head = newList.head.next;
	    return newList;
	}
	
	/**
	 * Takes a linked list and removes duplicates and returns a new list
	 * @return
	 */
	public LinkedList removeDuplicatesHashMap() {
		Node<Integer> list = this.head;
		Node<Integer> ret = this.head;
		HashMap<Integer, Boolean> values = new HashMap<Integer, Boolean>();
		
		while (list != null) {
			Node<Integer> prev = list;
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
		return new LinkedList(ret);
	}
	
	public LinkedList removeDuplicatesConstantMemory() {
		return null;
	}
	
	/**
	 * returns the linked list in reverse
	 * @return
	 */
	public LinkedList reverseIterative() {
		Node<Integer> curr = this.head;
		if (curr == null || curr.next == null) //if one element
			return new LinkedList(curr);
		else {	//more than one
			Node<Integer> prev = curr;
			curr = curr.next;
			prev.next = null;
			while (curr != null) {
				Node<Integer> temp = curr.next;
				curr.next = prev;
				prev = curr;
				curr = temp;
			}
			this.head = prev;
			LinkedList returnVal = new LinkedList(prev);
			return returnVal;
		}
		
	}
	
}
