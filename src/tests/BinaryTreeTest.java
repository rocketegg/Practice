package tests;

import java.util.ArrayList;
import java.util.HashMap;

import main.adt.BinaryTree;
import main.Node;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class BinaryTreeTest extends TestCase {
	
	BinaryTree b;
	int v1 = 0;
	int v2 = 0;
	private static final int MAX = 100000;
	HashMap<Integer, Integer> values;
	
	@Before
	public void setUp(){ 
		b = new BinaryTree();
		values = new HashMap<Integer,Integer>();
		
		for (int x = 0; x < MAX; x++) {
			Node n = new Node(Math.round((float) (Math.random()*100)));
			//Node n = new Node(MAX-x);
			//System.out.println("inserting: " + x/2);
			Integer key = n.value;
			if (!values.containsKey(key)) {
				values.put(key, new Integer(1));
			} else {
				Integer count = values.get(key);
				values.put(key, count+1);	
			}
			if (x == MAX/2)
				v1 = n.value;
			else if (x == MAX/2 + 1)
				v2 = n.value;
			
			b.insert(key);
		}
	}
	
//	@Test
//	public void testPrintLevels() {
//		b.printLevels();
//	}

	@Test
	public void testFind() {
		System.out.println("TESTING FIND ==========");
		//b.printInOrder();
		for (Integer i : values.keySet()) {
			for (int j = 0; j < values.get(i); j++) {
				//System.out.println("\tFinding " + i);
				assertTrue(b.find(i) != null);
			}

		}
	}
	
	@Test
	public void testDelete() {
		System.out.println("TESTING DELETE ==========");
		int count = b.getNumNodes();
		for (Integer i : values.keySet()) {
			for (int j = 0; j < values.get(i); j++) {
				//System.out.println("\tDELETING " + i);
				b.delete(i);
				count --;
			}
			assertTrue(b.find(i) == null);
			assertTrue(count == b.getNumNodes());
		}
	}
	
	
	@Test
	public void testLCA() {
		/*b.printLevels();
		Node lca = b.leastCommonAncestor(v1, v2);
		System.out.println("LCA of: " + (v1) + " + " + v2 + " = " + lca);
		*/
	}
}
