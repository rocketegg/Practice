package main.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Stack {
	
	private Queue<Integer> q1;
	private Queue<Integer> q2;
	
	public Stack() {
		q1 = new ConcurrentLinkedQueue<Integer> ();
		q2 = new ConcurrentLinkedQueue<Integer> ();
	}
	
	public void fastPush(int n) {
		if (q1.isEmpty()) {
			q2.add(n);
		} else {
			q1.add(n);
		}
	}
	
	public int slowPop() {
		if (q1.isEmpty()) {
			return dequeueAllButOne(q2, q1);
		} else {
			return dequeueAllButOne(q1, q2);
		}
	}
	
	private int dequeueAllButOne(Queue<Integer> q1, Queue<Integer> q2) {
		while (q1.size() > 1) {
			q2.add(q1.remove());
		}
		return q1.remove();
	}
	
	public void slowPush(int n) {
		if (q1.isEmpty()) {
			q1.add(n);
			while (!q2.isEmpty()) {
				q1.add(q2.remove());
			}
		} else {
			q2.add(n);
			while (!q1.isEmpty()) {
				q2.add(q1.remove());
			}
		}
		
	}
	
	public int fastPop() {
		if (q1.isEmpty())
			return q2.remove();
		else 
			return q1.remove();
	}
	
	public void printBoth() {
		System.out.print("Q1: ");
		for (int i : q1) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print("Q2: ");
		for (int i : q2) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
	
}

