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
		System.out.println("\n--Print--");
		System.out.println("List 1:");
		list1.print();
		System.out.println("List 2:");
		list2.print();
	}
	
	public void testCombine() {
		System.out.println("\n--Testing Combination--");
		System.out.println("List 1 combined with List 2:");
		LinkedList two = list1.combineLists(list2);
		two.print();
		System.out.println("List 2 combined with List 1:");
		LinkedList three = list2.combineLists(list1);
		three.print();
	}
	
	public void testRemoveDuplicatesHashMap() {
		System.out.println("\n--Testing Removing Duplicates--");
		LinkedList three = list2.combineLists(list1);
		System.out.println("List 3:");
		three.insert(new Node<Integer>(11));
		three.print();
		System.out.println("List 3 with No Dups:");
		LinkedList three_nodups = three.removeDuplicatesHashMap();
		three_nodups.print();
	}
	
	public void testReverse() {
		System.out.println("\n--Testing Reversal--");
		LinkedList three = list2.combineLists(list1);
		System.out.println("List 3:");
		three.print();
		System.out.println("Reversed:");
		three.reverseIterative().reverseIterative().print();
		three.print();
		/*System.out.println("Reversed Twice More (should be same):");
		three.reverseIterative().reverseIterative().print();
		three.print();
		System.out.println("Reversing once more for good measure:");
		three.reverseIterative().print();
		*/
	}
	
}
