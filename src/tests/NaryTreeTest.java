package tests;

import java.util.ArrayList;

import main.adt.NaryTree;
import main.adt.element.MultiNode;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class NaryTreeTest extends TestCase {

	private NaryTree<Integer> myTree;
	private ArrayList<Integer> treeValues;
	
	@Before
	public void setUp() {
		myTree = new NaryTree<Integer>(new MultiNode<Integer>(5));
		treeValues = new ArrayList<Integer> ();
		constructTree(myTree.getRoot(), 3, 3);
	}
	
	@Before
	private void constructTree(MultiNode<Integer> root, int maxLevels, int rangeOfLeavesPerLevel) {
		if (maxLevels > 0) {	
			for (int x = 0; x < TestUtils.randomInt(1, rangeOfLeavesPerLevel); x++) {
				Integer toAdd = TestUtils.randomInt(0, 100);
				treeValues.add(toAdd);
				//System.out.println("Adding " + toAdd);
				root.addLeaf(new MultiNode<Integer>(toAdd));
			}
			for (MultiNode<Integer> n: root.getLeaves()) {
				constructTree(n, maxLevels-1, rangeOfLeavesPerLevel);
			}
		}
	}
	/*
	public void testPrint() {
		System.out.println("TESTING PRINT =============================");
		//myTree.printTree(0);
	}*/
	
	@Test
	public void testHasValue() {
		System.out.println("TESTING HAS VALUE DFS =============================");
		for (Integer n: treeValues) {
			if (n % 2 == 0) {
				n = n/2;
			}
			System.out.println("Has value: " + n + "? " + myTree.hasValueDFS(n));
		}
		myTree.printTree(0);
	}
	
	@Test
	public void testHasValueBFS() {
		System.out.println("TESTING HAS VALUE BFS =============================");
		for (Integer n: treeValues) {
			if (n % 2 == 0) {
				n = n/2;
			}
			System.out.println("Has value: " + n + "? " + myTree.hasValueBFS(n));
		}
		myTree.printTree(0);
	}
	
	@Test
	public void testTreePreOrder() {
		System.out.println("1 - PREORDER TRAVERSAL =============================");
		myTree.printTreePreOrder();
		
		System.out.println("1 - SUM CHILDREN TRAVERSAL =============================");
		myTree.printSumTotals();
	}
	
}
