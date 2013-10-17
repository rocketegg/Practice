package main.util;

public class LinkedList {

	public Node<Integer> head;
	
	public LinkedList() {
		head = null;
	}
	
	public LinkedList(Node n) {
		head = n;
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
		Node t = head;
		while (t != null) {
			System.out.print(t.value + " ");
			t = t.next;
		}
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
	
}
