package main.adt.element;

import java.util.ArrayList;

public class MultiNode<T> {
	private T t;
	private ArrayList<MultiNode<T>> leaves;
	
	public MultiNode(T value) {
		t = value;
		leaves = new ArrayList<MultiNode<T>>();
	}
	
	public MultiNode(T value, ArrayList<MultiNode<T>> leaves) {
		t = value;
		this.leaves = leaves;
	}
	
	public ArrayList<MultiNode<T>> getLeaves () {
		return leaves;
	}
	
	public T getValue() {
		return t;
	}
	
	/*
	 * Traversals
	 */
	
	/**
	 * Visits each node in preorder traversal 
	 */
	public void printPreorder() {
		printPreorderOffset(0);
	}
	
	private void printPreorderOffset(int offset) {
		for (int x = 0; x < offset; x++)
			System.out.print("  ");
		print();
		if (leaves.size() != 0) {
			for (MultiNode<T> leaf: leaves) {
				leaf.printPreorderOffset(offset+1);
			}
		}
	}
	
	public void printSumValues() {
		sumUpValuesOffset(0);
	}
	
	/**
	 * Prints the sum of children in preorder format
	 * @param offset
	 */
	private void sumUpValuesOffset(int offset) {
		for (int x = 0; x < offset; x++)
			System.out.print("  ");
		System.out.println(sumUpValuesOfChildrenPostOrder());
		if (leaves.size() != 0) {
			for (MultiNode<T> leaf: leaves) {
				leaf.sumUpValuesOffset(offset+1);
			}
		}
	}
	
	/**
	 * Visits each node in postorder traversal to sum up
	 * value in subtrees and prints structure
	 */
	private Integer sumUpValuesOfChildrenPostOrder() {
		Integer total = (Integer)t;
		if (leaves.size() != 0) {
			for (MultiNode<T> leaf: leaves) {
				Integer leafValue = leaf.sumUpValuesOfChildrenPostOrder();
				total += leafValue;
			}
		}
		return total;
	}
	
	public void addLeaf(MultiNode<T> insert) {
		leaves.add(insert);
	}
	
	public void print() {
		System.out.println(t);
	}
}
