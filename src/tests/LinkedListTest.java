package tests;

import org.junit.Before;

import junit.framework.TestCase;
import main.util.LinkedList;
import main.util.Node;

public class LinkedListTest extends TestCase {

	LinkedList<Integer> list1;
	LinkedList<Integer> list2;
	
	@Before
	public void setUp() {
		list1 = new LinkedList<Integer>(new Node<Integer>(10));
		list1.insert(new Node<Integer>(7));
		list1.insert(new Node<Integer>(6));
		list1.insert(new Node<Integer>(5));
		list1.insert(new Node<Integer>(2));
		list1.insert(new Node<Integer>(1));
		
		
		list2 = new LinkedList<Integer>(new Node<Integer>(11));
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
		System.out.println("\n--Testing Combination Impl 1--");
		System.out.println("List 1 combined with List 2:");
		LinkedList<Integer> two = list1.combineLists(list2);
		two.print();
		System.out.println("List 2 combined with List 1:");
		LinkedList<Integer> three = list2.combineLists(list1);
		three.print();
	}
	
	public void testCombineAlternate() {
		System.out.println("\n--Testing Merge --");
		System.out.println("List 1 merged with List 2:");
		LinkedList<Integer> two = list1.merge(list2);
		two.print();
		System.out.println("List 2 merged with List 1:");
		LinkedList<Integer> three = list2.merge(list1);
		three.print();
	}
	
	public void testRemoveDuplicatesHashMap() {
		System.out.println("\n--Testing Removing Duplicates--");
		LinkedList<Integer> three = list2.combineLists(list1);
		System.out.println("List 3:");
		three.insert(new Node<Integer>(11));
		three.print();
		System.out.println("List 3 with No Dups:");
		LinkedList<Integer> three_nodups = three.removeDuplicatesHashMap();
		three_nodups.print();
	}
	
	public void testReverse() {
		System.out.println("\n--Testing Reversal--");
		LinkedList<Integer> three = list2.combineLists(list1);
		System.out.println("List 3:");
		three.print();
		System.out.println("Reversed Twice:");
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
