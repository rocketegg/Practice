package tests;

import main.BinaryTree;
import main.Node;

import org.junit.Before;

import junit.framework.TestCase;

public class BinaryTreeTest extends TestCase {
	
	BinaryTree b;
	
	@Before
	public void setUp(){ 
		b = new BinaryTree();
		
		for (int x = 0; x < 20; x++) {
			Node n = new Node(Math.round((float) (Math.random()*100)));
			System.out.println("inserting: " + n.value);
			if (x == 0)
				 b = new BinaryTree(n);
			else{
				b.insert(n);
			}
		}
	}
	
	public void testPrintLevels() {
		b.printLevels();
	}

}
