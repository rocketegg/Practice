package tests;

import org.junit.Before;

import junit.framework.TestCase;
import main.util.LinkedList;
import main.util.Node;

public class LinkedListTest extends TestCase {

	LinkedList list1;
	LinkedList list2;
	
	@Before
	public void setUp() {
		list1 = new LinkedList(new Node<Integer>(10));
		list1.insert(new Node<Integer>(7));
		list1.insert(new Node<Integer>(6));
		list1.insert(new Node<Integer>(5));
		list1.insert(new Node<Integer>(2));
		list1.insert(new Node<Integer>(1));
		
		
		list2 = new LinkedList(new Node<Integer>(11));
		list2.insert(new Node<Integer>(9));
		list2.insert(new Node<Integer>(8));
		list2.insert(new Node<Integer>(5));
		list2.insert(new Node<Integer>(4));
		list2.insert(new Node<Integer>(1));
	}
	
	public void testPrint() {
		list1.print();
		System.out.println();
		list2.print();
		System.out.println();
	}
	
	public void testCombine() {
		LinkedList two = list1.combineLists(list2);
		two.print();
		LinkedList three = list2.combineLists(list1);
		System.out.println();
		three.print();
	}
	
}
