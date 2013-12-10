package main.adt.element;

public class TreeNode<K extends Comparable<K>> {

	public K value;
	public TreeNode<K> left;
	public TreeNode<K> right;
	public TreeNode<K> parent;
	
	public TreeNode (K k) {
		this.value = k;
		left = null;
		right = null;
		parent = null;
	}
	
	public TreeNode (K k, TreeNode<K> p) {
		this.value = k;
		left = null;
		right = null;
		parent = p;
	}
	
	public void insert(K k) {
		insert(k, this);
	}
	
	private void insert(K k, TreeNode<K> parent) {
		if (k.compareTo(value) > 0) {
			if (right == null) {
				right = new TreeNode<K>(k, this);
			} else {
				right.insert(k, this);
			}
		} else {
			if (left == null) {
				left = new TreeNode<K>(k, this);
			} else {
				left.insert(k, this);
			}
		}
	}
	
	public TreeNode<K> findMin() {
		if (left == null) 
			return this;
		else 
			return left.findMin();
	}
	
	public TreeNode<K> findMax() {
		if (right == null) 
			return this;
		else 
			return right.findMax();
	}
	
	public TreeNode<K> find(K k) {
		if (value.compareTo(k) == 0) {
			return this;
		} else {
			if (k.compareTo(value) <= 0) {
				return (left != null) ? left.find(k) : null;
			} else {
				return (right != null) ? right.find(k) : null;
			}
		}
	}
	
	public Integer numNodes() {
		if (value == null) {
			return 0;
		} else {
			if (left != null && right != null) {
				return 1 + left.numNodes() + right.numNodes();
			} else if (left == null && right != null) {
				return 1 + right.numNodes();
			} else if (right == null && left != null) {
				return 1 + left.numNodes();
			} else {
				return 1;
			}
		}
	}
	
	public void inOrder() {
		if (left != null) left.inOrder();
		System.out.println(this);
		if (right != null) right.inOrder();
	}
	
	@Override
	public String toString() {
		return "[" + value + "]";
	}
	
}
