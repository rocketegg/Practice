package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BinaryTree {

	Node root;
	BinaryTree left;
	BinaryTree right;
	ArrayList<int[]> dataRep;
	
	public BinaryTree() {
		this.root = new Node();
		left = null;
		right = null;
		dataRep = new ArrayList<int[]>();
	}
	
	public BinaryTree(Node node) {
		this.root = node;
		left = null;
		right = null;
		dataRep = new ArrayList<int[]>();
	}
	
	//inserts in order
	public void insert(Node n) {
		if (findNode(this, n.value) == null) {
			insertOkay(n);
		}
 	}
	
	public Node findNode(BinaryTree bt, int value) {
		if (bt == null) {
			return null;
		} else if (bt.root.value == value) {
			return bt.root;
		} else {
			if (bt.root.value < value) 
				return findNode(bt.right, value);
			else
				return findNode(bt.left, value);
		}
	}
	
	private void insertOkay(Node n) {
		if (n.value > root.value) {
			if (right == null) {
				right = new BinaryTree(n);
			} else {
				right.insert(n);
			}
		} else {
			if (left == null) {
				left = new BinaryTree(n);
			} else {
				left.insert(n);
			}
		}
	}
	
	public void inOrder() {
		if (left != null) left.inOrder();
		System.out.println(root.value);
		if (right != null) right.inOrder();
	}
	
	public void preOrder() {
		System.out.println(root.value);
		if (left != null) left.preOrder();
		if (right != null) right.preOrder();
	}
	
	public int countNodes() {
		if (left == null && right == null) 
			return 1;
		else if (left == null) 
			return 1 + right.countNodes();
		else if (right == null )
			return 1 + left.countNodes();
		else 
			return 1 + left.countNodes() + right.countNodes();
	}
	
	public int countLevels() {
		if (left == null && right == null) 
			return 0;
		else if (left == null) 
			return 1 + right.countLevels();
		else if (right == null)
			return 1 + left.countLevels();
		else
			return 1 + max(left.countLevels(), right.countLevels());
	}
	
	private int max (int left, int right) {
		if (left < right)
			return right;
		return left;
	}
	
	/**
	 * Prints levels of the binary tree.  Uses a BinaryTreeTuple which
	 * contains a BinaryTree and an int representing the "level" in the master tree
	 * 
	 * This algorithm basically follows BFS
	 */
	public void printLevels() {
		ConcurrentLinkedQueue<BinaryTreeTuple> q = new ConcurrentLinkedQueue<BinaryTreeTuple>(); 
		if (root != null) {
			q.add(new BinaryTreeTuple(this, 1));
			int prevLevel = 1;
			while (!q.isEmpty()) {
				BinaryTreeTuple t = q.remove();
				BinaryTree n = t.bt;
				if (t.level > prevLevel) {
					System.out.print("\nLevel " + t.level + ": ");
					prevLevel = t.level;
				} 
				System.out.print(n.root + " ");
				if (n.left != null)
					q.add(new BinaryTreeTuple(n.left, t.level + 1));
				if (n.right != null)
					q.add(new BinaryTreeTuple(n.right, t.level + 1));
			}
		}
	}
	
	private class BinaryTreeTuple {
		public BinaryTree bt;
		public int level;
		
		public BinaryTreeTuple(BinaryTree bt, int level) {
			this.bt = bt;
			this.level = level;
		}
	}
}
