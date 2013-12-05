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
	
	public void addLeaf(MultiNode<T> insert) {
		leaves.add(insert);
	}
}
