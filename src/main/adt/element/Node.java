package main.adt.element;

public class Node<T> implements Comparable<T> {
	public T value;
	public Node<T> next;
	
	public Node(T t) {
		this.value = t;
		this.next = null;
	}
	
	public Node() {
		this.value = null;
		this.next = null;
	}

	@Override
	public int compareTo(Object t) {
		if (t instanceof Node) {
			T value = (T) ((Node)t).value;
			if (this.value != null && value != null) {
				if (value instanceof Integer && this.value instanceof Integer) {
					if ((Integer)this.value < (Integer)value) {
						return -1;
					} else if ((Integer)this.value > (Integer)value) {
						return 1;
					} else {
						return 0;
					}
				}
			}
			return 0;
		} else {
			return 0; //invalid type error
		}
	}
}
