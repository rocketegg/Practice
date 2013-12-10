package main.adt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import main.Node;
import main.adt.element.TreeNode;

public class BinaryTree {
	
	public TreeNode<Integer> root;
	public Integer numNodes;
	
	public BinaryTree() {
		root = null;
		numNodes = 0;
	}
	
	public BinaryTree(Integer value) {
		root = new TreeNode<Integer>(value);
		numNodes = 1;
	}
	
	public void insert(Integer value) {
		if (numNodes == 0) {
			root = new TreeNode<Integer>(value);
		} else {
			root.insert(value);
		}
		numNodes++;
	}
	
	public Integer getNumNodes() {
		if (root == null) 
			return 0;
		return root.numNodes();
	}
	
	public void printInOrder() {
		if (root != null) root.inOrder();
	}
	
	public TreeNode<Integer> find(Integer value) {
		if (root == null) 
			return null;
		return root.find(value);
	}
	

	public boolean delete(Integer value) {
		TreeNode<Integer> d = find(value);
		//System.out.println("found d: " + d);
		if (d == null) {
			return false;
		} else if (d.parent == null) {	//the root
			//System.out.println("d is root");
			TreeNode<Integer> dummy = new TreeNode<Integer> (value+1);
			dummy.left = root;
			root.parent = dummy;
			root = dummy;
			
			//this.printInOrder();
			delete(value);
			root = dummy.left;
			if (root != null) root.parent = null;
		} else {
			if (d.left == null && d.right == null) {
				if (d == d.parent.left) {
					d.parent.left = null;
				} else {
					d.parent.right = null;
				}
			} else if (d.left != null && d.right != null) {
				
				TreeNode<Integer> e = d.right.findMin();
				//System.out.println("min: " + e);
				d.value = e.value;
				
				if (e.parent == d) {
					if (e.right != null) {
						d.right = e.right;
						e.right.parent = d;
					} else {
						d.right = null;
					}
				} else {
					if (e.right != null) {
						e.parent.left = e.right;
						e.right.parent = e.parent;
					} else {
						e.parent.left = null;
					}
				}
			} else {
				if (d == d.parent.left) {
					if (d.left == null) {
						d.parent.left = d.right;
						d.right.parent = d.parent;
					} else {
						d.parent.left = d.left;
						d.left.parent = d.parent;
					}
				} else {
					if (d.left == null) {
						d.parent.right = d.right; 
						d.right.parent = d.parent;
					} else {
						d.parent.right = d.left;
						d.left.parent = d.parent;
					}
				}
			}
		}
		return true;
	}
	
}
