package main.adt;

import java.util.concurrent.ConcurrentLinkedQueue;

import main.adt.element.MultiNode;

public class NaryTree<T> {

	private MultiNode<T> root;
	
	public NaryTree() {
		root = null;
	}
	
	public NaryTree(MultiNode<T> root) {
		this.root = root;
	}
	
	public void setRoot(MultiNode<T> root) {
		this.root = root;
	}
	
	public MultiNode<T> getRoot() {
		return root;
	}
	
	/**
	 * Inserts a value as a leaf of a the NaryTree
	 * @param t
	 */
	public void insertLeaf(T t) {
		MultiNode<T> insert = new MultiNode<T>(t);
		root.addLeaf(insert);
	}
	
	/**
	 * Checks whether the N-ary tree has a value in it using BFS
	 * @param t
	 * @return
	 */
	public boolean hasValueBFS(T t) {
		ConcurrentLinkedQueue<MultiNode<T>> nodes = new ConcurrentLinkedQueue<MultiNode<T>>();
		nodes.add(root);
		while (!nodes.isEmpty()) {
			MultiNode<T> n = nodes.remove();
			if (n.getValue().equals(t)) {
				return true;
			} else {
				for (MultiNode<T> a: n.getLeaves()) 
					nodes.add(a);
			}
		}
		return false;
	}
	
	public boolean hasValueDFS(T t) {
		//System.out.println(root.getValue() + " / " + t);
		if (root.getValue().equals(t)) {
			return true;	
		} else {
			boolean returnVal = false;
			for (MultiNode<T> node: root.getLeaves()) {
				NaryTree<T> newBranch = new NaryTree<T>(node);
				returnVal = returnVal || newBranch.hasValueDFS(t);
			}
			return returnVal;
		}
	}
	
	/**
	 * Prints the tree hierarchically
	 */
	public void printTree(int numtabs) {
		for (int x = 0; x < numtabs; x++) 
			System.out.print(" ");
		System.out.print(root.getValue() + "\n");
		
		if (!root.getLeaves().isEmpty()) {
			for (MultiNode<T> leaf: root.getLeaves()) {
				NaryTree<T> newBranch = new NaryTree<T>(leaf);
				newBranch.printTree(numtabs+1);
			}
		} 
	}
	
	/**
	 * Prints tree in pre order notation
	 */
	public void printTreePreOrder() {
		root.printPreorder();
	}
	
	/**
	 * Prints tree but with sum of children
	 */
	public void printSumTotals() {
		root.printSumValues();
	}
	
}
