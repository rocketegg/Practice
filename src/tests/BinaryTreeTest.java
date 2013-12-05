package tests;

import main.adt.BinaryTree;
import main.Node;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class BinaryTreeTest extends TestCase {
	
	BinaryTree b;
	int v1 = 0;
	int v2 = 0;
	private static final int MAX = 10;
	
	@Before
	public void setUp(){ 
		b = new BinaryTree();
		
		for (int x = 0; x < MAX; x++) {
			Node n = new Node(Math.round((float) (Math.random()*100)));
			//Node n = new Node(MAX-x);
			System.out.println("inserting: " + n.value);
			if (x == MAX/2)
				v1 = n.value;
			else if (x == MAX/2 + 1)
				v2 = n.value;
			if (x == 0)
				 b = new BinaryTree(n);
			else{
				b.insert(n);
			}
		}
	}
	
//	@Test
//	public void testPrintLevels() {
//		b.printLevels();
//	}

	@Test
	public void testLCA() {
		b.printLevels();
		Node lca = b.leastCommonAncestor(v1, v2);
		System.out.println("LCA of: " + (v1) + " + " + v2 + " = " + lca);
	}
}
