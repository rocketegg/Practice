package main;

public class BinaryTree {

	Node root;
	BinaryTree left;
	BinaryTree right;
	
	public BinaryTree() {
		this.root = new Node();
		left = null;
		right = null;
	}
	
	public BinaryTree(Node node) {
		this.root = node;
		left = null;
		right = null;
	}
	
	//inserts in order
	public void insert(Node n) {
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
}
