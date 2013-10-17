package main;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	public void printTree() {
		int levels = this.countLevels();
		int arraySize = (int) Math.pow(2, levels);
		for (int x = 0; x < levels+1; x++) {
			dataRep.add(x, new int[arraySize]); 	//initialize with zeros
		}
		insertIntoArray(arraySize/2, 0, this, this.dataRep);
		
		for (int x = 0; x < levels+1; x++) {
			int[] row = dataRep.get(x); 	//initialize with zeros
			int count = 0;
			char [] nums = new char[row.length];
			for (int y = 0; y < row.length; y++){
				if (row[y] == 0)
					System.out.print(" ");
				else{
					System.out.print(row[y]);
					//System.out.print("looking for " + row[y]);
					BinaryTree bt = findNode(this, row[y]);
					if (bt != null) {
						if (bt.left != null) {
							nums[y-1] = '/';
						}
						if (bt.right != null) {
							nums[y+2] = '\\';
						}
					}
				}
			}
			System.out.println();
			for (int z = 0; z < row.length; z++) {
				if (nums[z] =='/' || nums[z]=='\\') {
					System.out.print(nums[z]);
				} else {
					System.out.print(" ");
				}	
			}
			System.out.println();
		}
	}
	
	private void insertIntoArray(int index, int level, BinaryTree b, ArrayList<int[]> dataRep) {
		if (b != null) {
			System.out.println("Ins: " + b.root.value + " @index: " + index + " @level: " + level);
			dataRep.get(level)[index] = b.root.value;
			insertIntoArray(index - 4, level+1, b.left, dataRep);
			insertIntoArray(index + 4, level+1, b.right, dataRep);
		}
	}
	
	public boolean isValidBinaryTree() {
		if (this.left != null && this.right != null) {
			return isSubTreeWithinRange(left, root.value, -1) && isSubTreeWithinRange(right, 10000, root.value);
		} else if (this.right == null) {
			return isSubTreeWithinRange(left, root.value, -1);
		} else if (this.left == null){
			return isSubTreeWithinRange(right, 10000, root.value);
		} else 
			return true;
	}
	
	private boolean isSubTreeWithinRange(BinaryTree b, int high, int low) {
		if (b == null)
			return true;
		else if (b.root.value > high || b.root.value < low)
			return false;
		else {
			return isSubTreeWithinRange(b.left, b.root.value, low) && 
					isSubTreeWithinRange(b.right, high, b.root.value);
		}
	}
	
	public BinaryTree findNode(BinaryTree bt, int value) {
		if (bt == null) {
			return null;
		} else if (bt.root.value == value) {
			//System.out.println("found value:" + value);
			return bt;
		} else if (bt.root.value < value) {
			return findNode(bt.right, value);
		} else if (bt.root.value >= value) {
			return findNode(bt.left, value);
		} else
			return null;
	}
}
